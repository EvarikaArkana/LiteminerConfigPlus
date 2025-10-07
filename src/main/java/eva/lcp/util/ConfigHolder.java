package eva.lcp.util;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigHolder {
    private static ConfigHolder holder;

    public static boolean isUsable() {return holder.usableStairs.get();}
    private ConfigHolder(ModConfigSpec.ConfigValue<Boolean> usableStairs) {
        this.usableStairs = usableStairs;
    }

    public static void setHolder(ModConfigSpec.ConfigValue<Boolean> usableStairs) {
        holder = new ConfigHolder(usableStairs);
    }

    private final ModConfigSpec.ConfigValue<Boolean> usableStairs;
}
