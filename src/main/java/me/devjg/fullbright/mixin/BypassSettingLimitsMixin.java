package me.devjg.fullbright.mixin;

import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(SimpleOption.DoubleSliderCallbacks.class)
public class BypassSettingLimitsMixin {
	@Inject(at = @At("HEAD"), method = "validate", cancellable = true)
	public void bypassValidation(Double value, CallbackInfoReturnable<Optional<Double>> cir) {
		cir.setReturnValue(Optional.of(value));
	}
}
