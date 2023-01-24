package site.moheng.mfui.widget.features;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetEdge;
import site.moheng.mfui.widget.enums.WidgetValue;

public class TextBoxWidget extends AbsWidget {

	public TextBoxWidget() {
		super();
		border(WidgetEdge.All, 4);
		height.put(WidgetValue.point(textRenderer.fontHeight + 8));
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		DrawableHelper.fill(matrices, layoutX(), layoutY(), layoutRight(), layoutBottom(), isFocused() ? -1 : -6250336);
		DrawableHelper.fill(matrices, layoutX() + 1, layoutY() + 1, layoutRight() - 1, layoutBottom() - 1, -16777216);

		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if(super.mouseClicked(mouseX, mouseY, button)) {
			return true;
		}

		if(isMouseOver(mouseX, mouseY)) {
			root().setFocus(this);
		} else {
			root().setFocus(null);
		}

		return false;
	}
}
