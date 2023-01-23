package site.moheng.mfui.util;

import net.minecraft.client.util.math.MatrixStack;

import static site.moheng.mfui.util.DrawUtil.*;

public interface RectDrawable {
	RectDrawable EMPTY = (matrix, x, y, width, height) -> {};
	NinePatchRenderer LIGHT_PANEL = new NinePatchRenderer(PANEL_TEXTURE, Size.square(5), Size.square(16), false);
	NinePatchRenderer DARK_PANEL = new NinePatchRenderer(DARK_PANEL_TEXTURE, Size.square(5), Size.square(16), false);
	NinePatchRenderer ACTIVE_BUTTON
			= new NinePatchRenderer(BUTTON_TEXTURE, 0, 0, Size.square(3), Size.square(58), Size.of(64, 192), true);

	NinePatchRenderer HOVERED_BUTTON
			= new NinePatchRenderer(BUTTON_TEXTURE, 0, 64, Size.square(3), Size.square(58), Size.of(64, 192), true);

	NinePatchRenderer BUTTON_DISABLED
			= new NinePatchRenderer(BUTTON_TEXTURE, 0, 128, Size.square(3), Size.square(58), Size.of(64, 192), true);

	void draw(MatrixStack matrix, int x, int y, int width, int height);
}
