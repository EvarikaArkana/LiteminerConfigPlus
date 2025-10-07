package eva.lcp.mixin.client.access;

import com.iamkaf.liteminer.shapes.Cycler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(Cycler.class)
public interface CyclerSetter {
    @Accessor("currentIndex")
    void currentIndex(int val);
}
