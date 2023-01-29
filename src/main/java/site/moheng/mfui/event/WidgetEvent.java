package site.moheng.mfui.event;

import site.moheng.mfui.widget.IWidgetHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class WidgetEvent<E, W extends IWidgetHandler> {
	private final W widget;
	private final List<Consumer<E>> listeners = new ArrayList<>();

	public WidgetEvent(W widget) {
		this.widget = widget;
	}

	public void emit(E event) {
		for (var listener : listeners) {
			listener.accept(event);
		}
	}

	public W on(Consumer<E> listener) {
		listeners.add(listener);
		return widget;
	}

	public W remove(Consumer<E> listener) {
		listeners.remove(listener);
		return widget;
	}
}
