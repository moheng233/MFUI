package site.moheng.mfui.widget.features;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;
import site.moheng.mfui.binding.WidgetAttribute;
import site.moheng.mfui.binding.source.StringBindingSource;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetEdge;
import site.moheng.mfui.widget.enums.WidgetValue;

public class TextBoxWidget extends AbsWidget {

	public final WidgetAttribute<String, TextBoxWidget> text = new WidgetAttribute<>("", this);
	private final StringBindingSource defaultText = new StringBindingSource();

	public TextBoxWidget() {
		super();
		text.binding(defaultText);
		border(WidgetEdge.All, 4);
		height.put(WidgetValue.point(textRenderer.fontHeight + 8));
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		DrawableHelper.fill(matrices, layout.x(), layout.y(), layout.right(), layout.bottom(), isFocused() ? -1 : -6250336);
		DrawableHelper.fill(matrices, layout.x() + 1, layout.y() + 1, layout.right() - 1, layout.bottom() - 1, -16777216);

		textRenderer.drawWithShadow(matrices, text.get(), layout.x() + 4, layout.y() + 4, 0xffffff);

		if(isFocused()) {
//			DrawableHelper.fill(matrices, layout.x() + 4, layout.bottom() - 4, layout.x() + 4 + 10, layout.bottom() - 3, -1);
			textRenderer.draw(matrices, "_", layout.x() + textRenderer.getWidth(text.get()) + 4, layout.y() + 5, 0xffffff);
		}

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

	@Override
	public boolean charTyped(char chr, int modifiers) {
		if (super.charTyped(chr, modifiers)) {
			return true;
		}

		if (isFocused()) {
			defaultText.append(chr);
		}

		return false;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (super.keyPressed(keyCode, scanCode, modifiers)) {
			return true;
		}

		if (!isFocused()) {
			return false;
		}

		if(keyCode == GLFW.GLFW_KEY_BACKSPACE) {
			defaultText.deleteAtChar(defaultText.length() - 1);
			return true;
		}

		return false;
	}
}
