package site.moheng.mfui.widget;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import site.moheng.mfui.binding.WidgetAttribute;


public class LabelWidget extends BaseWidget {
	public final WidgetAttribute<Text, LabelWidget> text = new WidgetAttribute<>(Text.empty(), this);
	public final WidgetAttribute<Integer, LabelWidget> color = new WidgetAttribute<>(0xffffff, this);

	public LabelWidget() {

	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
		updateStyle();
		textRenderer.draw(matrices, text.get(), layoutX(), layoutY(), color.get());
	}

	public void updateStyle() {
		width(textRenderer.getWidth(text.get()));
		height(textRenderer.fontHeight);
	}
}
