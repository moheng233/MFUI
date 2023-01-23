package site.moheng.mfui.util;

import net.minecraft.client.util.math.MatrixStack;

public interface RectDrawable {
	RectDrawable EMPTY = (matrix, x, y, width, height) -> {};

	void draw(MatrixStack matrix, int x, int y, int width, int height);
}
