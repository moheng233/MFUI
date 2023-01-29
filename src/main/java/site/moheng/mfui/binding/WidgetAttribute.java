package site.moheng.mfui.binding;

import site.moheng.mfui.widget.AbsWidget;

import java.util.ArrayList;
import java.util.List;


public class WidgetAttribute<S, W extends AbsWidget> implements IWidgetAttribute<S, W> {
	protected final W widget;
	protected List<IEvent<S>> listeners = new ArrayList<>();
	protected IObservable<S> binding;
	protected S defaultData;

	public WidgetAttribute(S defaultData, W widget) {
		this.defaultData = defaultData;
		this.widget = widget;
	}

	public IObservable<S> getBinding() {
		return binding;
	}

	public void emitBindingChange() {
		if(binding != null) {
			binding.submit();
		}
	}

	public W binding(IObservable<S> source) {
		cleanBinding();
		binding = source;
		binding.addListener(this::change);

		return widget;
	}

	public void cleanBinding() {
		if (binding != null) {
			binding.removeListener(this::change);
			binding = null;
		}
	}

	public void change(IObservable<S> source) {
		submit();
	}

	public W put(S data) {
		if (binding == null) {
			defaultData = data;
			submit();
		} else {
			binding.set(data);
		}

		return widget;
	}

	@Override
	public void set(S data) {
		put(data);
	}

	@Override
	public List<IEvent<S>> getListeners() {
		return listeners;
	}

	@Override
	public S get() {
		if (binding == null) {
			return defaultData;
		}
		return binding.get();
	}
}
