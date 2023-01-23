package site.moheng.mfui.binding;

import org.jetbrains.annotations.Nullable;
import site.moheng.mfui.widget.IWidget;


public class WidgetAttribute<S extends IBindingSource<S>, W extends IWidget> {
	protected final W widget;

	protected S binding;
	@Nullable
	protected IBindingSource.IEvent<S> event = null;

	public WidgetAttribute(S defaultSource, W widget) {
		this.binding = defaultSource;
		this.widget = widget;
	}

	public void onChange(IBindingSource.IEvent<S> event) {
		binding.addListener(event);
	}

	public S source() {
		return binding;
	}

	public W binding(S source) {
		cleanBinding();
		binding = source;

		return widget;
	}

	public W cleanBinding() {
		if (binding != null) {
			if (event != null) {
				binding.removeListener(event);
				event = null;
			}
			binding = null;
		}

		return widget;
	}
}
