package site.moheng.mfui.widget.features;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import site.moheng.mfui.binding.WidgetAttribute;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.enums.WidgetType;
import site.moheng.mfui.widget.enums.WidgetValue;


public class LabelWidget extends AbsWidget {
	public final WidgetAttribute<Text, LabelWidget> text = new WidgetAttribute<>(Text.empty(), this);
	public final WidgetAttribute<Integer, LabelWidget> color = new WidgetAttribute<>(0xffffff, this);

	public LabelWidget() {
		WidgetType.Text.setNodeType(this);
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
		updateStyle();
		textRenderer.draw(matrices, text.get(), layoutX(), layoutY(), color.get());
	}

	public void updateStyle() {
		width.put(WidgetValue.point(textRenderer.getWidth(text.get())));
		height.put(WidgetValue.point(textRenderer.fontHeight));
	}
}
