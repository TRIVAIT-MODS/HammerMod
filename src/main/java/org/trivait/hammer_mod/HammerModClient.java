package org.trivait.hammer_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import org.trivait.hammer_mod.util.HammerMode;
import org.trivait.hammer_mod.util.OverlayRenderer;

public class HammerModClient implements ClientModInitializer {

    private static KeyBinding altKey;

    @Override
    public void onInitializeClient() {
        altKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hammer_mod.alt", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.hammer_mod"));

        HudRenderCallback.EVENT.register((ctx, tickDelta) -> OverlayRenderer.render(ctx, 0));
        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);
    }

    private void onClientTick(MinecraftClient client) {
        OverlayRenderer.tick(client);

        if (client.player == null) return;

        if (isAltPressed()) {
            OverlayRenderer.ensureShownFromAlt(client);
        }
        if (client.player.isSneaking()) {
            OverlayRenderer.show(HammerMode.SINGLE, true);
        }
    }

    public static boolean isAltPressed() { return altKey != null && altKey.isPressed(); }
}
