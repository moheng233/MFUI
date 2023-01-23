package site.moheng.mfui.widget;

import net.minecraft.client.util.math.MatrixStack;
import site.moheng.mfui.binding.WidgetAttribute;
import site.moheng.mfui.binding.source.IntBindingSource;
import site.moheng.mfui.binding.source.TextBindingSource;


public class LabelWidget extends BaseWidget {
	public final WidgetAttribute<TextBindingSource, LabelWidget> text = new WidgetAttribute<>(new TextBindingSource(), this);
	public final WidgetAttribute<IntBindingSource, LabelWidget> color = new WidgetAttribute<>(new IntBindingSource(), this);

	public LabelWidget() {

	}

	public void updateStyle() {
		width(textRenderer.getWidth(text.source().get()));
		height(textRenderer.fontHeight);
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
		updateStyle();
		textRenderer.draw(matrices, text.source().get(), layoutX(), layoutY(), color.source().get());
	}
}
