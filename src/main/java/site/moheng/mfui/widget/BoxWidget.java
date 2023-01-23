package site.moheng.mfui.widget;

import net.minecraft.client.util.math.MatrixStack;
import site.moheng.mfui.binding.WidgetAttribute;
import site.moheng.mfui.util.RectDrawable;

public class BoxWidget extends BaseWidget {
	public final WidgetAttribute<RectDrawable, BoxWidget> background = new WidgetAttribute<>(RectDrawable.EMPTY, this);

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		background.get().draw(matrices, layoutX(), layoutY(), layoutWidth(), layoutHeight());
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
	}
}
