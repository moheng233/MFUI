package site.moheng.mfui.binding;

import site.moheng.mfui.widget.AbsWidget;

public interface IWidgetAttribute<S, W extends AbsWidget> extends IObservable<S> {
	W binding(IObservable<S> source);
	W put(S data);

	@Override
	default void set(S data) {
		put(data);
	}
}
