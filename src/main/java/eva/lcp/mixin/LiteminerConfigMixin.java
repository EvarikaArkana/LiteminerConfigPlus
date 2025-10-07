package eva.lcp.mixin;

import com.iamkaf.liteminer.config.LiteminerConfig;
import com.llamalad7.mixinextras.sugar.Local;
import eva.lcp.util.ConfigHolder;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LiteminerConfig.class, remap = false)
public class LiteminerConfigMixin {
    @Inject(
            method = "<init>",
            at = @At(
                    "TAIL"
            )
    )
    private void init(CallbackInfo ci, @Local(argsOnly = true) ModConfigSpec.Builder builder) {
        ConfigHolder.setHolder(builder.translation("Better Stairs").comment(":)").define("better_stairs", true));
    }
}
