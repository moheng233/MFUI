package site.moheng.mfui.binding.source;


import site.moheng.mfui.binding.AbsObservable;
import site.moheng.mfui.binding.ComputedObservable;

public final class StringBuilderObservable extends AbsObservable {
	public final StringBuilder data = new StringBuilder();
	public final ComputedObservable<StringBuilderObservable, String> asString;

	private String boxed = null;

	public StringBuilderObservable() {
		this.asString = computed(StringBuilderObservable::getString);
	}

	@Override
	public void setChange() {
		boxed = null;
		super.setChange();
	}

	public StringBuilder get() {
		return data;
	}

	public String getString() {
		if (boxed == null) {
			boxed = data.toString();
		}
		return boxed;
	}
}