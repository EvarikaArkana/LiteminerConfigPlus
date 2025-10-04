package eva.lcp.util;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigHolder {
    private static ConfigHolder holder;

    public static int getIndex() {return holder.initialScrollIndex.get().index;}
    private ConfigHolder(ModConfigSpec.ConfigValue<ListEntries> indexHolder) {
        this.initialScrollIndex = indexHolder;
    }

    public static void setHolder(ModConfigSpec.ConfigValue<ListEntries> indexHolder) {
        holder = new ConfigHolder(indexHolder);
    }

    public ModConfigSpec.ConfigValue<ListEntries> initialScrollIndex;
}
