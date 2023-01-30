package site.moheng.mfui.widget.features;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import site.moheng.mfui.binding.attribute.IntWidgetAttribute;
import site.moheng.mfui.binding.attribute.SimpleValuedWidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetType;
import site.moheng.mfui.widget.enums.WidgetValue;


public class LabelWidget extends AbsWidget {
	public final SimpleValuedWidgetAttribute<Text, LabelWidget> text = new SimpleValuedWidgetAttribute<>(Text.empty(), this);
	public final IntWidgetAttribute<LabelWidget> color = new IntWidgetAttribute<>(this);

	public LabelWidget() {
		WidgetType.Text.setNodeType(this);
		color.set(0xffffff);
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
		updateStyle();
		textRenderer.draw(matrices, text.get(), layout.x(), layout.y(), color.get());
	}

	public void updateStyle() {
		width.set(WidgetValue.point(textRenderer.getWidth(text.get())));
		height.set(WidgetValue.point(textRenderer.fontHeight));
	}
}
