package me.devjg.fullbright.gui;

import java.net.URI;

import com.mojang.brigadier.context.CommandContext;

import me.devjg.fullbright.Fullbright;
import me.devjg.fullbright.common.Settings;
import me.devjg.fullbright.common.Utils;
import me.devjg.fullbright.gui.components.FullbrightSlider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

// TODO: Instantaneous/Transitional strings

@Environment(EnvType.CLIENT)
public class FullbrightScreen extends Screen {
    public static boolean show = false;
    private int fontColour = 0xAC829FFF;

    public FullbrightScreen() {
        super(Text.of("Fullbright"));
    }

    public FullbrightSlider transitionSpeedSlider;

    public ButtonWidget changeWithLightLevelButton;
    public ButtonWidget notificationsButton;
    public ButtonWidget discordButton;

    @Override
    public void init() {
        final int cornerButtonSizes = 100;
        final int centreButtonSizes = 150;

        transitionSpeedSlider = new FullbrightSlider(this.width/2 - centreButtonSizes/2, this.height/2 - 9, centreButtonSizes, 20, "Transition Speed", 0.0, 5.0);
        transitionSpeedSlider.setTooltip(Tooltip.of(Text.of("Choose how quickly you transition between brightness levels")));

        changeWithLightLevelButton = ButtonWidget.builder(Text.of("Change w/ Light Level: false"), button -> {
            // TODO
        })
            .size(centreButtonSizes, 20)
            .position(this.width/2 - centreButtonSizes/2, height/2 + 13)
            .tooltip(Tooltip.of(Text.of("Enable this to automatically change brightness depending on light level")))
            .build();

        notificationsButton = ButtonWidget.builder(notificationText(), button -> {
            Settings.notifications = !Settings.notifications;
            notificationsButton.setMessage(notificationText());
        })
            .size(cornerButtonSizes, 20)
            .position(width - cornerButtonSizes - 2, height - 44)
            .tooltip(Tooltip.of(Text.of("Enable Fullbright notifications in chat")))
            .build();

        discordButton = ButtonWidget.builder(Text.of("Discord"), button -> {
            URI discordUri = Utils.getUri(Fullbright.DISCORD_LINK);

            Text discordLink = Text.literal("§7§nClick here to join our Discord!")
                .setStyle(Style.EMPTY
                    .withClickEvent(new ClickEvent.OpenUrl(discordUri))
                    .withHoverEvent(new HoverEvent.ShowText(Text.of(Fullbright.DISCORD_LINK))));

            Text discordLinkMessage = Utils.formChatMessage(discordLink);
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(discordLinkMessage);
            close();
        })
            .size(cornerButtonSizes, 20)
            .position(width - cornerButtonSizes - 2, height - 22)
            .tooltip(Tooltip.of(Text.of("Click here to join our discord")))
            .build();

        addDrawableChild(transitionSpeedSlider);
        addDrawableChild(changeWithLightLevelButton);

        addDrawableChild(notificationsButton);
        addDrawableChild(discordButton);
    }

    @Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
        MatrixStack matrices = context.getMatrices();

        matrices.push();
        matrices.scale(2.0f, 2.0f, 1.0f);
        context.drawCenteredTextWithShadow(this.textRenderer, "The Future Of", width/4, 3, fontColour);
        context.drawCenteredTextWithShadow(this.textRenderer, "Fullbright", width/4, 4 + this.textRenderer.fontHeight, fontColour);
        matrices.pop();

        context.drawCenteredTextWithShadow(this.textRenderer, "Mode: Instantaneous", width/2, height/2 - this.textRenderer.fontHeight*2 - 2, fontColour);
	}

    private static Text notificationText() {
        return Text.of("Notifications: " + Settings.notifications);
    }

    public static int openGui(CommandContext<FabricClientCommandSource> context) {
        show = true;
        return 1;
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (FullbrightScreen.show) {
                client.setScreen(new FullbrightScreen());
                show = false;
            }
        });
    }
}
