package org.trivait.hammer_mod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.trivait.hammer_mod.HammerModClient;
import org.trivait.hammer_mod.item.custom.HammerItem;

public final class OverlayRenderer {

    private static boolean active = false;
    private static HammerMode mode = HammerMode.DEFAULT;
    private static boolean forceSingle = false;
    private static int timer = 0; // 0..20

    public static void show(HammerMode m, boolean shift) {
        active = true;
        mode = shift ? HammerMode.SINGLE : m;
        forceSingle = shift;
        timer = Math.min(timer + 8, 20);
    }

    public static void ensureShownFromAlt(MinecraftClient client) {
        if (client.player == null) return;
        ItemStack stack = client.player.getMainHandStack();
        if (!(stack.getItem() instanceof HammerItem)) return;

        boolean sneaking = client.player.isSneaking();
        HammerMode current = sneaking ? HammerMode.SINGLE : HammerModeUtils.get(stack);
        show(current, sneaking);
    }

    public static void tick(MinecraftClient client) {
        if (!active) return;
        boolean keep = HammerModClient.isAltPressed();
        if (!keep) {
            timer = Math.max(0, timer - 2);
            if (timer == 0) active = false;
        } else {
            timer = Math.min(20, timer + 1);
        }
    }

    public static void render(DrawContext ctx, float tickDelta) {
        if (!active || ctx == null) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.textRenderer == null) return;

        int sw = ctx.getScaledWindowWidth();
        int sh = ctx.getScaledWindowHeight();

        // чуть выше хотбара
        int y = sh - 60;

        float alpha = timer / 20f;
        ctx.setShaderColor(1f, 1f, 1f, alpha);

        HammerMode[] modes = forceSingle ? new HammerMode[]{HammerMode.SINGLE}
                : new HammerMode[]{HammerMode.DEFAULT, HammerMode.CUBE, HammerMode.SINGLE};

        // центрируем текстовую строку
        int spacing = 80;
        int totalWidth = (modes.length - 1) * spacing;
        int xStart = (sw - totalWidth) / 2;

        for (int i = 0; i < modes.length; i++) {
            HammerMode m = modes[i];
            int ox = xStart + i * spacing;

            boolean selected = (m == mode);
            Text label = Text.literal(m.getDisplayName());
            int tw = client.textRenderer.getWidth(label);
            int tx = ox - tw / 2;
            int color = selected ? 0xFF0000 : 0xFFFFFF;

            ctx.drawText(client.textRenderer, label, tx, y, color, false);
        }

        ctx.setShaderColor(1f, 1f, 1f, 1f);
    }
}
