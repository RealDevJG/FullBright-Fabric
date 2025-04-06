package me.devjg.fullbright;

import java.util.List;

import com.llamalad7.mixinextras.lib.apache.commons.tuple.Pair;

import me.devjg.fullbright.common.Settings;
import me.devjg.fullbright.common.Utils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class FullbrightHandler {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    private static final Pair<String, Double> DIMMEST = Pair.of("§4DIMMEST", -0.1);
    private static final Pair<String, Double> DIM = Pair.of("§cDIM", 3.0);
    private static final Pair<String, Double> BRIGHT = Pair.of("§eBRIGHT", 6.5);
    private static final Pair<String, Double> BRIGHTEST = Pair.of("§aBRIGHTEST", 12.0);

    private static final List<Pair<String, Double>> levels = List.of(DIMMEST, DIM, BRIGHT, BRIGHTEST);
    private static int currentLevel = 0;

    private static Pair<String, Double> getCurrentLevel() {
        return levels.get(currentLevel);
    }

    private static Pair<String, Double> getNextLevel() {
        currentLevel = (currentLevel + 1) % levels.size();
        return levels.get(currentLevel);
    }

    private static void instantChange(double toLevel) {
        client.options.getGamma().setValue(toLevel);
        client.options.write();
    }

    private static void keyPressed() {
        instantChange(getNextLevel().getValue());

        if (Settings.notifications) {
            Text notification = Utils.formChatMessage("§7set to", getCurrentLevel().getKey());
            client.inGameHud.getChatHud().addMessage(notification);
        }
    }

    public static void register() {
        KeyBindingHelper.registerKeyBinding(Settings.fullbrightKeybind);
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (Settings.fullbrightKeybind.wasPressed())
                keyPressed();
        });
    }
}
