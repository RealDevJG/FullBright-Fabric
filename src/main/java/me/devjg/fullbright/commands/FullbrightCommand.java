package me.devjg.fullbright.commands;

import com.mojang.brigadier.CommandDispatcher;

import me.devjg.fullbright.gui.FullbrightScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

@Environment(EnvType.CLIENT)
public class FullbrightCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("fb").executes(FullbrightScreen::openGui));
        dispatcher.register(ClientCommandManager.literal("fullbright").executes(FullbrightScreen::openGui));
    }
}
