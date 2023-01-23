package site.moheng.mfui.binding.source;

import net.minecraft.text.Text;
import site.moheng.mfui.binding.IBindingSource;

import java.util.ArrayList;
import java.util.List;

public class TextBindingSource implements IBindingSource<TextBindingSource> {
	protected List<IEvent<TextBindingSource>> listeners = new ArrayList<>();
	protected Text data;

	public TextBindingSource(Text data) {
		this.data = data;
	}

	public TextBindingSource() {
		this.data = Text.empty();
	}

	public Text get() {
		return data;
	}

	public void set(Text data) {
		this.data = data;
		submit();
	}

	public void submit() {
		for (var consumer : listeners) {
			consumer.accept(this);
		}
	}

	@Override
	public List<IEvent<TextBindingSource>> getListeners() {
		return listeners;
	}
}
