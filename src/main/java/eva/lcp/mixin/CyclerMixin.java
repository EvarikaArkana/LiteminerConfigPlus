package eva.lcp.mixin;

import com.iamkaf.liteminer.shapes.Cycler;
import eva.lcp.util.ConfigHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(value = Cycler.class, remap = false)
public abstract class CyclerMixin {

    @Shadow private int currentIndex;

    @Unique boolean firstAccess = true;

    @Inject(
            method = "getCurrentItem",
            at = @At("HEAD")
    )
    private <T> void replaceInitial0(CallbackInfoReturnable<T> cir) {
        if (firstAccess) {
            firstAccess = false;
            currentIndex = ConfigHolder.getIndex();
        }
    }

    @Inject(
            method = "nextItem",
            at = @At("HEAD")
    )
    private <T> void replaceInitial1(CallbackInfoReturnable<T> cir) {
        if (firstAccess) {
            firstAccess = false;
            currentIndex = ConfigHolder.getIndex();
        }
    }

    @Inject(
            method = "previousItem",
            at = @At("HEAD")
    )
    private <T> void replaceInitial2(CallbackInfoReturnable<T> cir) {
        if (firstAccess) {
            firstAccess = false;
            currentIndex = ConfigHolder.getIndex();
        }
    }

    @Inject(
            method = "getCurrentIndex",
            at = @At("HEAD")
    )
    private <T> void replaceInitial3(CallbackInfoReturnable<T> cir) {
        if (firstAccess) {
            firstAccess = false;
            currentIndex = ConfigHolder.getIndex();
        }
    }
}