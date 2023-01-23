package site.moheng.mfui.widget;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static org.lwjgl.util.yoga.Yoga.YGDirectionLTR;

public class WidgetScreen extends Screen implements IWidgetScreen {
	protected final ScreenWidget root = new ScreenWidget();
	protected boolean opened = false;

	protected WidgetScreen(Text title) {
		super(title);
	}

	@Override
	public void tick() {
		super.tick();
		root.tick();
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		layout();
		root.drawChildren(matrices, mouseX, mouseY, 0, delta);
	}

	@Override
	public float getScreenWidth() {
		return width;
	}

	@Override
	public float getScreenHeight() {
		return height;
	}

	@Override
	public int getScreenOwnerDirection() {
		return YGDirectionLTR;
	}

	@Override
	public ScreenWidget root() {
		return root;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if(super.mouseClicked(mouseX, mouseY, button)) {
			return true;
		}
		return root.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if(super.mouseReleased(mouseX, mouseY, button)) {
			return true;
		}
		return root.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if(super.keyPressed(keyCode, scanCode, modifiers)) {
			return true;
		}
		return root.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		if(super.keyReleased(keyCode, scanCode, modifiers)) {
			return true;
		}
		return root.keyReleased(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean charTyped(char chr, int modifiers) {
		if(super.charTyped(chr, modifiers)) {
			return true;
		}
		return root.charTyped(chr, modifiers);
	}

	@Override
	public void widget(ScreenWidget root) {

	}

	@Override
	protected void init() {
		super.init();
		if(!opened) {
			widget(root());
			opened = true;
		}
	}

	@Override
	public void close() {
		opened = false;
		super.close();
	}
}
