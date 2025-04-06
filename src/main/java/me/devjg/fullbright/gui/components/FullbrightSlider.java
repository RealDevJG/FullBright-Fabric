package me.devjg.fullbright.gui.components;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class FullbrightSlider extends SliderWidget {
    private String message;
    private double maxValue;

    public FullbrightSlider(int x, int y, int width, int height, String message, double value, double maxValue) {
        super(x, y, width, height, Text.literal(message), value);
        this.message = message;
        this.maxValue = maxValue;

        setMessage(createMessage());
    }

    private Text createMessage() {
        return Text.of(message + ": " + String.format("%.2f", value * maxValue));
    }

    @Override
    protected void updateMessage() {
        setMessage(createMessage());
    }

    @Override
    protected void applyValue() {
        System.out.println(value * 5);
    }
}
