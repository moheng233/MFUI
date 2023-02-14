package site.moheng.mfui;

import net.minecraft.text.Text;
import org.joml.Vector2i;
import site.moheng.mfui.binding.source.ListObservable;
import site.moheng.mfui.binding.source.SimpleValuedObservable;
import site.moheng.mfui.widget.AbsWidget;
import site.moheng.mfui.widget.ScreenWidget;
import site.moheng.mfui.widget.WidgetScreen;
import site.moheng.mfui.widget.enums.WidgetAlign;
import site.moheng.mfui.widget.enums.WidgetFlexDirection;
import site.moheng.mfui.widget.enums.WidgetJustify;
import site.moheng.mfui.widget.features.ButtonWidget;
import site.moheng.mfui.widget.features.LabelWidget;
import site.moheng.mfui.widget.features.TextBoxWidget;
import site.moheng.mfui.widget.logic.ForeachWidget;

import java.util.Random;

public class ListTestScreen extends WidgetScreen {
	public Random random = new Random();

	public final ListObservable<SimpleValuedObservable<String>> list = new ListObservable<>();

	protected ListTestScreen(Text title) {
		super(title);
	}

	@Override
	public void widget(ScreenWidget root) {
		root.flexDirection.set(WidgetFlexDirection.Row)
				.justifyContent.set(WidgetJustify.Center)
				.alignItems.set(WidgetAlign.Center)
				.child(new ForeachWidget<>(this::builder)
						.data.binding(list))
				.child(new ButtonWidget()
						.click.on(this::add)
						.child(new TextBoxWidget()
								.text.set("Add")));
	}

	private AbsWidget builder(SimpleValuedObservable<String> str) {
		return new LabelWidget()
				.text.binding(str.computed((SimpleValuedObservable<String> s) -> Text.of(s.get())));
	}

	public void add(Vector2i pos) {
		list.add(new SimpleValuedObservable<>(String.valueOf(random.nextInt())));
	}
}
