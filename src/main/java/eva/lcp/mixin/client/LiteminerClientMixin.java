package eva.lcp.mixin.client;

import com.iamkaf.liteminer.LiteminerClient;
import eva.lcp.mixin.client.access.CyclerSetter;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static eva.lcp.util.ClientConfigHolder.getIndex;

@Mixin(value = LiteminerClient.class, remap = false)
public class LiteminerClientMixin {
    @Shadow
    private static boolean currentState;

    @Inject(
            method = "onPostTick",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/iamkaf/liteminer/LiteminerClient;currentState:Z"
            )
    )
    private static void setIndex(Minecraft minecraft, CallbackInfo ci) {
        if (!currentState && getIndex() >= 0)
            ((CyclerSetter) LiteminerClient.shapes).currentIndex(getIndex());
    }
}
