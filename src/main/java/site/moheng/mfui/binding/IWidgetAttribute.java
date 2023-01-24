package site.moheng.mfui.binding;

import site.moheng.mfui.widget.IWidgetHandler;

public interface IWidgetAttribute<S, W extends IWidgetHandler> extends IBindingSource<S> {
	W binding(IBindingSource<S> source);
	W put(S data);

	@Override
	default void set(S data) {
		put(data);
	}
}
