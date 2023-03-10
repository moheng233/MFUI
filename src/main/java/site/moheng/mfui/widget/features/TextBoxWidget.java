package site.moheng.mfui.widget.features;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;
import site.moheng.mfui.binding.attribute.StringBuilderWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetEdge;
import site.moheng.mfui.widget.enums.WidgetValue;

public class TextBoxWidget extends AbsWidget {

	public static final int BORDER = 4;

	public final StringBuilderWidgetAttribute<TextBoxWidget> text = new StringBuilderWidgetAttribute<>(this);
	protected int curer = 0;

	protected int firstChar = 0;

	public TextBoxWidget() {
		super();
		border(WidgetEdge.All, BORDER);
		height.set(WidgetValue.point(textRenderer.fontHeight + BORDER * 2));
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		DrawableHelper.fill(matrices, layout.x(), layout.y(), layout.right(), layout.bottom(), isFocused() ? -1 : -6250336);
		DrawableHelper.fill(matrices, layout.x() + 1, layout.y() + 1, layout.right() - 1, layout.bottom() - 1, -16777216);

//		ScissorStack.STACK.push(layout.x() + BORDER, layout.y() + BORDER, layout.width() - BORDER * 2, layout.height() - BORDER * 2, matrices);
		var rt = textRenderer.trimToWidth(text.getString().substring(firstChar), layout.width() - BORDER * 2);

		textRenderer.drawWithShadow(matrices, rt, layout.x() + BORDER, layout.y() + BORDER, 0xffffff);

		if (isFocused()) {
			var first = textRenderer.getWidth(rt.substring(0, Math.min(curer - firstChar, rt.length())));
			if (curer == text.getString().length()) {
				textRenderer.drawWithShadow(matrices, "_", layout.x() + first + BORDER, layout.y() + BORDER, 0xffffff);
			} else {
				DrawableHelper.fill(matrices, layout.x() + BORDER + first, layout.y() + BORDER, layout.x() + BORDER + 1 + first, layout.bottom() - BORDER, -1);
			}
		}

//		ScissorStack.STACK.pop();

		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (super.mouseClicked(mouseX, mouseY, button)) {
			return true;
		}

		if (isMouseOver(mouseX, mouseY)) {
			root().setFocus(this);
			return true;
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

		if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
			if (curer > 0) {
				text.getBinding().data.deleteCharAt(curer - 1);
				text.getBinding().setChange();
				curer -= 1;
				if(curer <= firstChar) {
					firstChar = MathHelper.clamp(firstChar - 1, 0, text.getString().length());
				}
			}
			return true;
		} else if (keyCode == GLFW.GLFW_KEY_LEFT) {
			curer = MathHelper.clamp(curer - 1, 0, text.getString().length());
			if(curer <= firstChar) {
				firstChar = MathHelper.clamp(firstChar - 1, 0, text.getString().length());
			}
		} else if (keyCode == GLFW.GLFW_KEY_RIGHT) {
			curer = MathHelper.clamp(curer + 1, 0, text.getString().length());
			if(textRenderer.getWidth(text.getString().substring(firstChar)) > layout.width()) {
				firstChar = MathHelper.clamp(firstChar + 1, 0, text.getString().length());
			}
		}

		return false;
	}

	@Override
	public boolean charTyped(char chr, int modifiers) {
		if (super.charTyped(chr, modifiers)) {
			return true;
		}

		if (isFocused()) {
			text.getBinding().data.insert(curer, chr);
			text.getBinding().setChange();
			curer += 1;
			if(textRenderer.getWidth(text.getString().substring(firstChar)) > layout.width() - BORDER * 2) {
				firstChar += 1;
			}
			return true;
		}

		return false;
	}
}
