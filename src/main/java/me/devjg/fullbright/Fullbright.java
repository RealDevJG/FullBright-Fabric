package me.devjg.fullbright;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.devjg.fullbright.commands.FullbrightCommand;
import me.devjg.fullbright.gui.FullbrightScreen;

@Environment(EnvType.CLIENT)
public class Fullbright implements ModInitializer {
	public static final String MOD_ID = "devjg-fullbright";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String DISCORD_LINK = "https://discord.gg/b35rQvS";

	@Override
	public void onInitialize() {
		LOGGER.info("Loading DevJG's FullBright Mod");

		FullbrightScreen.register();
		FullbrightHandler.register();
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> FullbrightCommand.register(dispatcher));
	}
}
