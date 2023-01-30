package site.moheng.mfui.binding;

public abstract class AbsValuedObservable<V> extends AbsObservable {

	public abstract V get();

	public abstract void set(V value);
}
