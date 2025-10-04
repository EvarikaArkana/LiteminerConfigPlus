package eva.lcp.access;

import eva.lcp.util.ListEntries;
import net.neoforged.neoforge.common.ModConfigSpec;

public interface ConfigAccess {
    ModConfigSpec.ConfigValue<ListEntries>[] initialScrollIndex =  new ModConfigSpec.ConfigValue[]{null};

    static int getIndex() {
        return initialScrollIndex[0].get().index;
    }
}
