package site.moheng.mfui.widget;

import site.moheng.mfui.event.WidgetEvent;
import site.moheng.mfui.widget.enums.WidgetValue;

public class ScreenWidget extends AbsWidget {
	public final WidgetEvent<AbsWidget, ScreenWidget> changeFocus = new WidgetEvent<>(this);

	protected AbsWidget focus = null;
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

	public boolean isFocused(AbsWidget widget) {
		return focus == widget;
	}

	public void setFocus(AbsWidget widget) {
		this.focus = widget;
		changeFocus.emit(widget);
	}
}
