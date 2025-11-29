package org.trivait.hammer_mod.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.HammerMod;

public final class NetworkHandler {

    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(SetHammerModePayload.ID, SetHammerModePayload.CODEC);
    }

    public static void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(SetHammerModePayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            MinecraftServer server = context.server();
            server.execute(() -> {
                if (player == null || player.isRemoved()) return;
                ItemStack stack = player.getMainHandStack();
                if (stack.isEmpty()) return;
                stack.set(ModDataComponentTypes.HAMMER_MODE, payload.mode());
            });
        });
    }

    public static void sendSetHammerMode(HammerMode mode) {
        ClientPlayNetworking.send(new SetHammerModePayload(mode));
    }

    // === Payload ===
    public record SetHammerModePayload(HammerMode mode) implements CustomPayload {
        public static final CustomPayload.Id<SetHammerModePayload> ID =
                new CustomPayload.Id<>(Identifier.of(HammerMod.MOD_ID, "set_hammer_mode"));

        public static final PacketCodec<PacketByteBuf, SetHammerModePayload> CODEC =
                PacketCodec.tuple(
                        PacketCodecs.STRING.xmap(HammerMode::valueOf, HammerMode::name),
                        SetHammerModePayload::mode,
                        SetHammerModePayload::new
                );

        @Override
        public Id<? extends CustomPayload> getId() { return ID; }
    }
}
