package eva.lcp.mixin;

import com.iamkaf.liteminer.Liteminer;
import com.iamkaf.liteminer.shapes.StaircaseUpWalker;
import com.iamkaf.liteminer.shapes.Walker;
import com.iamkaf.liteminer.tags.TagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

import static eva.lcp.util.ConfigHolder.isUsable;

@Mixin(value = StaircaseUpWalker.class, remap = false)
public abstract class StaircaseUpWalkerMixin implements Walker {
    @Shadow
    public final Set<BlockPos> VISITED = new HashSet<>();

    @Inject(
            method = "searchBlocks",
            at = @At("HEAD"),
            cancellable = true
    )
    private void searchBlocksBetter(
            Player player,
            Level level,
            BlockPos myPos,
            BlockPos absoluteOrigin,
            HashSet<BlockPos> blocksToCollapse,
            Block originBlock,
            Direction direction,
            CallbackInfo ci
    ) {
        if (!isUsable()) return;
        if (this.VISITED.size() < Liteminer.CONFIG.blockBreakLimit.get()) {
            if (!this.VISITED.contains(myPos)) {
                BlockState state = level.getBlockState(myPos);
                if (!state.is(Blocks.AIR) && !TagHelper.isExcludedBlock(state)) {
                    for(BlockPos cursor = myPos; blocksToCollapse.size() < Liteminer.CONFIG.blockBreakLimit.get(); cursor = cursor.relative(direction).above()) {

                        boolean shouldMineCursor = this.shouldMine(player, level, cursor);
                        boolean shouldMineBelowCursor = this.shouldMine(player, level, cursor.below());
                        boolean shouldMineAboveCursor = this.shouldMine(player, level, cursor.above());

                        if (!(shouldMineCursor || shouldMineBelowCursor || shouldMineAboveCursor)) {
                            break;
                        }

                        if (blocksToCollapse.size() >= Liteminer.CONFIG.blockBreakLimit.get()) break;

                        if (shouldMineCursor) {
                            blocksToCollapse.add(cursor);
                        }

                        if (blocksToCollapse.size() >= Liteminer.CONFIG.blockBreakLimit.get()) break;

                        if (shouldMineBelowCursor) {
                            blocksToCollapse.add(cursor.below());
                        }

                        if (blocksToCollapse.size() >= Liteminer.CONFIG.blockBreakLimit.get()) break;

                        if (shouldMineAboveCursor) {
                            blocksToCollapse.add(cursor.above());
                        }

                    }

                    blocksToCollapse.add(myPos);
                }
            }
        }
        ci.cancel();
    }
}
