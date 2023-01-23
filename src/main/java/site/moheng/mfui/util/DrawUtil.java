package site.moheng.mfui.util;

import net.minecraft.util.Identifier;

public class DrawUtil {
	public static final Identifier PANEL_TEXTURE = new Identifier("mfui", "textures/gui/panel.png");
	public static final Identifier DARK_PANEL_TEXTURE = new Identifier("mfui", "textures/gui/dark_panel.png");
	public static final NinePatchRenderer LIGHT_PANEL = new NinePatchRenderer(PANEL_TEXTURE, Size.square(5), Size.square(16), false);
	public static final NinePatchRenderer DARK_PANEL = new NinePatchRenderer(DARK_PANEL_TEXTURE, Size.square(5), Size.square(16), false);

}
