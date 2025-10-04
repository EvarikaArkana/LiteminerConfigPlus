package eva.lcp;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.event.events.client.ClientRawInputEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LcpClient implements ClientModInitializer {
    public static final String MOD_ID = LcpMain.MOD_ID + "-client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final KeyMapping FAKE_UP;
    private static final KeyMapping FAKE_DOWN;
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if (FAKE_UP.consumeClick()) {
                ClientRawInputEvent.MOUSE_SCROLLED.invoker().mouseScrolled(
                        client,
                        0.0,
                        0.1
                );
            }
            if (FAKE_DOWN.consumeClick()) {
                ClientRawInputEvent.MOUSE_SCROLLED.invoker().mouseScrolled(
                        client,
                        0.0,
                        -0.1
                );
            }
        });
    }

    static {
        FAKE_UP = KeyBindingHelper.registerKeyBinding(new KeyMapping("Fake Scroll Up", InputConstants.Type.KEYSYM, -1, "key.categories.liteminer"));
        FAKE_DOWN = KeyBindingHelper.registerKeyBinding(new KeyMapping("Fake Scroll Down", InputConstants.Type.KEYSYM, -1, "key.categories.liteminer"));
    }
}