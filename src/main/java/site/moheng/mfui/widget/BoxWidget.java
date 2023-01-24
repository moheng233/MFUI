package site.moheng.mfui.widget;

import net.minecraft.client.util.math.MatrixStack;
import site.moheng.mfui.binding.WidgetAttribute;
import site.moheng.mfui.util.RectDrawable;
import site.moheng.mfui.widget.enums.WidgetEdge;

public class BoxWidget extends AbsWidget {
	public final WidgetAttribute<RectDrawable, BoxWidget> background = new WidgetAttribute<>(RectDrawable.EMPTY, this);

	public BoxWidget() {
		border(WidgetEdge.All, 4);
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		background.get().draw(matrices, layoutX(), layoutY(), layoutWidth(), layoutHeight());
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
	}
}
