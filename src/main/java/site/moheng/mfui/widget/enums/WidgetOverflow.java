package site.moheng.mfui.widget.enums;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetOverflow {
	Visible(YGOverflowVisible),
	Hidden(YGOverflowHidden),
	Scroll(YGOverflowScroll);

	private final int number;

	WidgetOverflow(int number) {
		this.number = number;
	}

	public final int get() {
		return number;
	}

	public static WidgetOverflow get(int number) {
		return switch (number) {
			case YGOverflowVisible -> Visible;
			case YGOverflowHidden -> Hidden;
			default -> Scroll;
		};
	}
}
