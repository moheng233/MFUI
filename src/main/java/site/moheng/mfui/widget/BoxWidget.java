package site.moheng.mfui.widget;

import net.minecraft.client.util.math.MatrixStack;
import site.moheng.mfui.binding.attribute.SimpleValuedWidgetAttribute;
import site.moheng.mfui.util.RectDrawable;
import site.moheng.mfui.widget.enums.WidgetEdge;

public class BoxWidget extends AbsWidget {
	public final SimpleValuedWidgetAttribute<RectDrawable, BoxWidget> background = new SimpleValuedWidgetAttribute<>(RectDrawable.EMPTY, this);

	public BoxWidget() {
		border(WidgetEdge.All, 4);
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		background.get().draw(matrices, layout.x(), layout.y(), layout.width(), layout.height());
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
	}
}
