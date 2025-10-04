package eva.lcp.mixin;

import com.iamkaf.liteminer.shapes.Cycler;
import eva.lcp.access.ConfigAccess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(value = Cycler.class, remap = false)
public abstract class CyclerMixin {

    @Shadow private int currentIndex;

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void replaceInitial(List initialItems, CallbackInfo ci) {
        currentIndex = ConfigAccess.getIndex();
    }
}