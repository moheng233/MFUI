# Minecraft Flex UI

# 介绍
基于 `facebook` 开源的 `Yoga` 布局引擎实现了基于 `Flex` 布局的 `Minecraft` GUI框架。

在 `Yoga` 的基础上实现了 `Flex` 标准的子集的同时也一并实现了多数的 `CSS` 属性。

采用 `MVC` 的设计理念，实现了数据的单向与双向绑定。

开发者可在此之上设计与实现复杂的UI界面，并且本项目可作为 `Yoga` 引擎嵌入到游戏中作为游戏布局引擎的不完全参考实现。

# 示例

![example](doc/example.png)

```java
public class WidgetTestScreen extends WidgetScreen {
	protected IntBindingSource count = new IntBindingSource();

	public WidgetTestScreen() {
		super(Text.of("test"));
	}

	@Override
	public void tick() {
		super.tick();
		count.set((count.getValue() + 20) % 765);
	}

	@Override
	public void widget(ScreenWidget root) {
		root.flexDirection(WidgetFlexDirection.Row)
				.justifyContent(WidgetJustify.Center)
				.alignItems(WidgetAlign.Center)
				.child(new BoxWidget()
						.background.put(RectDrawable.LIGHT_PANEL)
						.child(new LabelWidget()
								.text.binding(
										count.computed((source) -> Text.of("Count:" + source)))
								.color.binding(
										count.computed((count) -> MathHelper.packRgb(count % 255, MathHelper.clamp(count - 255, 0, 255), MathHelper.clamp(count - 510, 0, 255)))
								)
								.margin(WidgetEdge.All, 4))
						.child(new ButtonWidget()
								.click.on((mouse) -> close())
								.child(new LabelWidget()
										.text.put(Text.of("取消"))))
				);
	}
}

```