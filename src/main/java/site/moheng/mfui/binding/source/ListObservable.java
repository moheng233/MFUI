package site.moheng.mfui.binding.source;

import org.jetbrains.annotations.NotNull;
import site.moheng.mfui.binding.AbsObservable;

import java.util.*;

public class ListObservable<V extends AbsObservable> extends AbsObservable implements List<V> {
	protected ArrayList<V> list = new ArrayList<>();

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@NotNull
	@Override
	public Iterator<V> iterator() {
		return list.iterator();
	}

	@NotNull
	@Override
	public Object @NotNull [] toArray() {
		return list.toArray();
	}

	@NotNull
	@Override
	public <T> T @NotNull [] toArray(@NotNull T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(V v) {
		if(list.add(v)) {
			setChange();
			return true;
		}

		return false;
	}

	@Override
	public boolean remove(Object o) {
		if(list.remove(o)) {
			setChange();
			return true;
		}

		return false;
	}

	@Override
	public boolean containsAll(@NotNull Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(@NotNull Collection<? extends V> c) {
		if(list.addAll(c)){
			setChange();
			return true;
		}

		return false;
	}

	@Override
	public boolean addAll(int index, @NotNull Collection<? extends V> c) {
		if(list.addAll(index, c)) {
			setChange();
			return true;
		}

		return false;
	}

	@Override
	public boolean removeAll(@NotNull Collection<?> c) {
		if(list.removeAll(c)) {
			setChange();
			return true;
		}

		return false;
	}

	@Override
	public boolean retainAll(@NotNull Collection<?> c) {
		if(list.retainAll(c)) {
			setChange();
			return true;
		}

		return false;
	}

	@Override
	public void clear() {
		list.clear();
		setChange();
	}

	@Override
	public V get(int index) {
		return list.get(index);
	}

	@Override
	public V set(int index, V element) {
		var ret = list.set(index, element);
		setChange();
		return ret;
	}

	@Override
	public void add(int index, V element) {
		list.add(index, element);
		setChange();
	}

	@Override
	public V remove(int index) {
		var ret = list.remove(index);
		setChange();
		return ret;
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@NotNull
	@Override
	public ListIterator<V> listIterator() {
		return list.listIterator();
	}

	@NotNull
	@Override
	public ListIterator<V> listIterator(int index) {
		return list.listIterator(index);
	}

	@NotNull
	@Override
	public List<V> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
}
