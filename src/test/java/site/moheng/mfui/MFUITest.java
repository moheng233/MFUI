package site.moheng.mfui;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MFUITest implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "mfui_test";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final KeyBinding OPEN_TEST_GUI_KEY = KeyBindingHelper.registerKeyBinding(
			new KeyBinding("key.mfui_test.open_test_gui_key", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "key.mfui_test.category")
	);

	@Override
	public void onInitialize() {

	}

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(this::openTestGui);
	}

	public void openTestGui(MinecraftClient client) {
		while (OPEN_TEST_GUI_KEY.wasPressed()) {
			client.executeSync(() -> client.setScreen(new WidgetTestScreen()));
		}
	}
}
