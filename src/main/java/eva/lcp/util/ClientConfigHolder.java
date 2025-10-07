package eva.lcp.util;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfigHolder {
    private static ClientConfigHolder holder;

    public static int getIndex() {return holder.initialScrollIndex.get().index;}
    private ClientConfigHolder(ModConfigSpec.ConfigValue<ListEntries> indexHolder) {
        this.initialScrollIndex = indexHolder;
    }

    public static void setHolder(ModConfigSpec.ConfigValue<ListEntries> indexHolder) {
        holder = new ClientConfigHolder(indexHolder);
    }

    private final ModConfigSpec.ConfigValue<ListEntries> initialScrollIndex;
}
