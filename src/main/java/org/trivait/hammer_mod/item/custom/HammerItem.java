package org.trivait.hammer_mod.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.trivait.hammer_mod.util.HammerMode;
import org.trivait.hammer_mod.util.HammerModeUtils;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material, Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(BlockPos origin, ServerPlayerEntity player, World world) {
        var stack = player.getMainHandStack();
        HammerMode mode = HammerModeUtils.get(stack);

        // Shift (isSneaking) принудительно SINGLE
        if (player.isSneaking()) {
            mode = HammerMode.SINGLE;
        }

        List<BlockPos> positions = new ArrayList<>();

        switch (mode) {
            case SINGLE -> {
                positions.add(origin);
            }
            case DEFAULT -> {
                // 3×3 в плоскости стороны удара
                Direction face = getHitFace(player);
                if (face == Direction.UP || face == Direction.DOWN) {
                    // плоскость XZ
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            positions.add(origin.add(dx, 0, dz));
                        }
                    }
                } else if (face == Direction.NORTH || face == Direction.SOUTH) {
                    // плоскость XY
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            positions.add(origin.add(dx, dy, 0));
                        }
                    }
                } else { // EAST / WEST
                    // плоскость YZ
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            positions.add(origin.add(0, dy, dz));
                        }
                    }
                }
            }
            case CUBE -> {
                // 3×3×3
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            positions.add(origin.add(dx, dy, dz));
                        }
                    }
                }
            }
        }
        return positions;
    }

    private static Direction getHitFace(ServerPlayerEntity player) {
        HitResult hit = player.raycast(20, 0, false);
        if (hit instanceof BlockHitResult bhr) {
            return bhr.getSide();
        }
        return Direction.NORTH;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("hammermode", HammerModeUtils.get(stack).getDisplayName()));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
