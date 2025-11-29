package org.trivait.hammer_mod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.trivait.hammer_mod.HammerModClient;
import org.trivait.hammer_mod.item.custom.HammerItem;
import org.trivait.hammer_mod.util.HammerMode;
import org.trivait.hammer_mod.util.HammerModeUtils;
import org.trivait.hammer_mod.util.NetworkHandler;
import org.trivait.hammer_mod.util.OverlayRenderer;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void hammer_mod$onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        if (!HammerModClient.isAltPressed()) return;

        ItemStack stack = client.player.getMainHandStack();
        if (!(stack.getItem() instanceof HammerItem)) return;

        boolean forward = !(vertical > 0);
        HammerMode newMode = forward ? HammerModeUtils.get(stack).next() : HammerModeUtils.get(stack).prev();

        // локально для UI
        HammerModeUtils.set(stack, newMode);
        // отправляем на сервер
        NetworkHandler.sendSetHammerMode(newMode);

        OverlayRenderer.show(newMode, client.player.isSneaking());

        ci.cancel(); // не крутим хотбар
    }
}
