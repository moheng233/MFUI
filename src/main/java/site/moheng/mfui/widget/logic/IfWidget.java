package site.moheng.mfui.widget.logic;

import net.minecraft.client.util.math.MatrixStack;
import site.moheng.mfui.binding.IObserver;
import site.moheng.mfui.binding.attribute.BooleanWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetDisplay;

/**
 * 根据功能显示或隐藏子小部件
 */
public class IfWidget extends AbsWidget implements IObserver {
	public final BooleanWidgetAttribute<IfWidget> basis = new BooleanWidgetAttribute<>(this);


	public IfWidget() {
		super();
		basis.set(true);
		basis.addObserver(this);
	}

	@Override
	public void drawChildren(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		if (basis.get()) {
			super.drawChildren(matrices, mouseX, mouseY, partialTicks, delta);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (basis.get()) {
			return super.mouseClicked(mouseX, mouseY, button);
		}
		return false;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (basis.get()) {
			return super.mouseReleased(mouseX, mouseY, button);
		}
		return false;
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		if (basis.get()) {
			return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
		}

		return false;
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		if (basis.get()) {
			return super.mouseScrolled(mouseX, mouseY, amount);
		}

		return false;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (basis.get()) {
			return super.keyPressed(keyCode, scanCode, modifiers);
		}

		return false;
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		if (basis.get()) {
			return super.keyReleased(keyCode, scanCode, modifiers);
		}

		return false;
	}

	@Override
	public boolean charTyped(char chr, int modifiers) {
		if (basis.get()) {
			return super.charTyped(chr, modifiers);
		}

		return false;
	}

	@Override
	public void change() {
		if (basis.get()) {
			WidgetDisplay.Flex.setDisplay(this);
		} else {
			WidgetDisplay.None.setDisplay(this);
		}
	}
}