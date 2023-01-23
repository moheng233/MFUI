package site.moheng.mfui.widget;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import site.moheng.mfui.util.DrawUtil;

public class BoxWidget extends BaseWidget {
	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		DrawUtil.LIGHT_PANEL.draw(matrices, layoutX(), layoutY(), layoutWidth(), layoutHeight());
		super.draw(matrices, mouseX, mouseY, partialTicks, delta);
	}
}
