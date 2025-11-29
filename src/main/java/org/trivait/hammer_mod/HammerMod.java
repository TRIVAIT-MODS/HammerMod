package org.trivait.hammer_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trivait.hammer_mod.item.ModItemGroups;
import org.trivait.hammer_mod.item.ModItems;
import org.trivait.hammer_mod.util.HammerUsageEvent;
import org.trivait.hammer_mod.util.ModDataComponentTypes;
import org.trivait.hammer_mod.util.NetworkHandler;

public class HammerMod implements ModInitializer {
	public static final String MOD_ID = "hammer_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModDataComponentTypes.registerData();
		ModItemGroups.register();
		ModItems.register();
		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		// Нетворкинг
		NetworkHandler.registerPayloads();
		NetworkHandler.registerServer();
	}
}
