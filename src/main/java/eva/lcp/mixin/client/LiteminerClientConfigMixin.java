package eva.lcp.mixin.client;

import com.iamkaf.liteminer.config.LiteminerClientConfig;
import com.llamalad7.mixinextras.sugar.Local;
import eva.lcp.access.ConfigAccess;
import eva.lcp.util.ListEntries;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = LiteminerClientConfig.class, remap = false)
public class LiteminerClientConfigMixin implements ConfigAccess {
    @Inject(
            method = "<init>",
            at = @At(
                    "TAIL"
            )
    )
    private void init(CallbackInfo ci, @Local(argsOnly = true) ModConfigSpec.Builder builder) {
        initialScrollIndex[0] = builder.translation("Default Mine Style").comment(":)").defineEnum("list_entries", ListEntries.Shapeless);
    }
}