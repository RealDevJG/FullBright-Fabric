package me.devjg.fullbright.common;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class Settings {
    public static boolean shouldIncrement;
    public static KeyBinding fullbrightKeybind = new KeyBinding("Cycle Brightness", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "https://discord.gg/b35rQvS");

    public static boolean notifications = true;
}
