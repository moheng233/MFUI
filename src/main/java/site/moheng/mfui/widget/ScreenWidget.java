package site.moheng.mfui.widget;

import org.jetbrains.annotations.Nullable;
import site.moheng.mfui.event.WidgetEvent;
import site.moheng.mfui.widget.enums.WidgetValue;

public class ScreenWidget extends AbsWidget {
	public final WidgetEvent<AbsWidget, ScreenWidget> changeFocus = new WidgetEvent<>(this);

	@Nullable protected AbsWidget focus = null;
	protected boolean debug = false;

	public ScreenWidget() {
		width.set(WidgetValue.percent(100));
		height.set(WidgetValue.percent(100));
	}

	@Override
	public ScreenWidget root() {
		return this;
	}

	public boolean debug() {
		return debug;
	}

	public void debug(boolean debug) {
		this.debug = debug;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if(super.mouseClicked(mouseX, mouseY, button)) {
			return true;
		}

		setFocus(null);
		return false;
	}

	public boolean isFocused(AbsWidget widget) {
		return focus == widget;
	}

	public void setFocus(@Nullable AbsWidget widget) {
		this.focus = widget;
		changeFocus.emit(widget);
	}
}
