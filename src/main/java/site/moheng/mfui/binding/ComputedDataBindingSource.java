package site.moheng.mfui.binding;

import java.util.List;
import java.util.function.Supplier;

public class ComputedDataBindingSource<S extends IBindingSource<S>, T extends IBindingSource<T>> implements  IBindingSource<T> {

	public final S sourceBinding;
	public final T targetBinding;

	protected IComputed<S, T> computed;

	public ComputedDataBindingSource(S source, Supplier<T> target, IComputed<S, T> computed) {
		this.sourceBinding = source;
		this.targetBinding = target.get();
		this.computed = computed;

		source.addListener(this::change);
	}

	void change(S source) {
		computed.accept(source, targetBinding);
	}

	@Override
	public List<IEvent<T>> getListeners() {
		return targetBinding.getListeners();
	}


	@FunctionalInterface
	public interface IComputed<S extends IBindingSource<?>, T extends IBindingSource<?>> {
		void accept(S source, T target);
	}
}
