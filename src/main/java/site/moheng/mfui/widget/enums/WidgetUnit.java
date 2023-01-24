package site.moheng.mfui.widget.enums;

import static org.lwjgl.util.yoga.Yoga.*;

public enum WidgetUnit {
	UNDEFINED(YGUnitUndefined),
	POINT(YGUnitPoint),
	PERCENT(YGUnitPercent),
	AUTO(YGUnitAuto);

	private final int number;

	WidgetUnit(int number) {
		this.number = number;
	}

	public final WidgetValue value(float value) {
		return new WidgetValue(value, this);
	}

	public final int get() {
		return number;
	}

	public static WidgetUnit get(int number) {
		return switch (number) {
			case YGUnitUndefined -> UNDEFINED;
			case YGUnitPoint -> POINT;
			case YGUnitPercent -> PERCENT;
			default -> AUTO;
		};
	}
}
