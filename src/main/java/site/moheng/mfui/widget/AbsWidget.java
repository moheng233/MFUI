package site.moheng.mfui.widget;

import com.google.common.hash.HashCode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.yoga.YGValue;
import site.moheng.mfui.binding.attribute.GetterWidgetAttribute;
import site.moheng.mfui.widget.enums.*;

import java.lang.ref.Cleaner;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.lwjgl.util.yoga.Yoga.*;

public abstract class AbsWidget implements IWidgetHandler {
	public static final YGValue VALUE = YGValue.create();
	public static final Cleaner CLEANER = Cleaner.create();
	protected final MinecraftClient client;
	protected final ItemRenderer itemRenderer;
	protected final TextRenderer textRenderer;
	private final long ygNode = YGNodeNew();
	public final GetterWidgetAttribute<WidgetValue, AbsWidget> width = new GetterWidgetAttribute<>(this) {

		@Override
		public AbsWidget put(WidgetValue data) {
			data.setWidth(widget);
			return widget;
		}

		@Override
		public WidgetValue get() {
			VALUE.clear();
			return WidgetValue.from(YGNodeStyleGetWidth(getYGNode(), VALUE));
		}
	};
	public final GetterWidgetAttribute<WidgetValue, AbsWidget> height = new GetterWidgetAttribute<>(this) {
		@Override
		public AbsWidget put(WidgetValue data) {
			data.setHeight(widget);
			return widget;
		}

		@Override
		public WidgetValue get() {
			VALUE.clear();
			return WidgetValue.from(YGNodeStyleGetHeight(getYGNode(), VALUE));
		}
	};
	public final GetterWidgetAttribute<WidgetDisplay, AbsWidget> flex = new GetterWidgetAttribute<>(this) {
		@Override
		public AbsWidget put(WidgetDisplay data) {
			data.setDisplay(widget);
			return widget;
		}

		@Override
		public WidgetDisplay get() {
			return WidgetDisplay.getDisplay(widget);
		}
	};
	public final GetterWidgetAttribute<WidgetJustify, AbsWidget> justifyContent = new GetterWidgetAttribute<>(this) {
		@Override
		public AbsWidget put(WidgetJustify data) {
			data.setJustifyContent(widget);
			return widget;
		}

		@Override
		public WidgetJustify get() {
			return WidgetJustify.getJustifyContent(widget);
		}
	};
	public final GetterWidgetAttribute<WidgetAlign, AbsWidget> alignContent = new GetterWidgetAttribute<>(this) {
		@Override
		public AbsWidget put(WidgetAlign data) {
			data.setAlignContent(widget);
			return widget;
		}

		@Override
		public WidgetAlign get() {
			return WidgetAlign.getAlignContent(widget);
		}
	};
	public final GetterWidgetAttribute<WidgetAlign, AbsWidget> alignSelf = new GetterWidgetAttribute<>(this) {
		@Override
		public AbsWidget put(WidgetAlign data) {
			data.setAlignSelf(widget);
			return widget;
		}

		@Override
		public WidgetAlign get() {
			return WidgetAlign.getAlignSelf(widget);
		}
	};
	public final GetterWidgetAttribute<WidgetAlign, AbsWidget> alignItems = new GetterWidgetAttribute<>(this) {
		@Override
		public AbsWidget put(WidgetAlign data) {
			data.setAlignItems(widget);
			return widget;
		}

		@Override
		public WidgetAlign get() {
			return WidgetAlign.getAlignItems(widget);
		}
	};
	public final GetterWidgetAttribute<WidgetFlexDirection, AbsWidget> flexDirection = new GetterWidgetAttribute<>(this) {
		@Override
		public AbsWidget put(WidgetFlexDirection data) {
			data.setFlexDirection(widget);
			return widget;
		}

		@Override
		public WidgetFlexDirection get() {
			return WidgetFlexDirection.getFlexDirect(widget);
		}
	};
	public AbsWidget parent;
	protected int hashcode;
	@Nullable
	protected ScreenWidget root = null;
	protected HashSet<AbsWidget> children = new HashSet<>();

	public AbsWidget() {
		long addr = ygNode;
		hashcode = HashCode.fromLong(addr).hashCode();
		AbsWidget.CLEANER.register(this, () -> YGNodeFree(addr));
		client = MinecraftClient.getInstance();
		itemRenderer = client.getItemRenderer();
		textRenderer = client.textRenderer;
	}

	public AbsWidget parent() {
		return parent;
	}

	public ScreenWidget root() {
		if (root == null) {
			root = parent.root();
		}

		return root;
	}

	public void root(ScreenWidget root) {
		this.root = root;
	}

	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		drawChildren(matrices, mouseX, mouseY, partialTicks, delta);
	}

	public AbsWidget child(AbsWidget child, int index) {
		YGNodeInsertChild(getYGNode(), child.getYGNode(), childCount());
		children.add(child);
		child.parent(this);
		return this;
	}

	public AbsWidget removeChild(AbsWidget child) {
		YGNodeRemoveChild(getYGNode(), child.getYGNode());
		children.remove(child);
		child.parent(null);
		return this;
	}

	public long getYGNode() {
		return ygNode;
	}

	public AbsWidget parent(AbsWidget parent) {
		this.parent = parent;
		return this;
	}

	public Set<AbsWidget> children() {
		return children;
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	public long getParentYGNode() {
		return YGNodeGetParent(getYGNode());
	}

	@MustBeInvokedByOverriders
	public void tick() {
		for (AbsWidget widget : children()) {
			widget.tick();
		}
	}

	public void drawChildren(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		matrices.push();
		matrices.translate(layout.x(), layout.y(), 0);
//		ScissorStack.STACK.push(0, 0, layout.width(), layout.height(), matrices);
		var tx = transformationX(mouseX);
		var ty = transformationY(mouseY);
		for (AbsWidget widget : children()) {
			widget.draw(matrices, (int) tx, (int) ty, partialTicks, delta);
		}
//		ScissorStack.STACK.pop();
		matrices.pop();
	}

	public boolean isFocused() {
		if (root() == null) {
			return false;
		}
		return root().isFocused(this);
	}

	public int layoutGlobalX() {
		return (parent() == null ? 0 : Objects.requireNonNull(parent()).layoutGlobalX()) + layout.x();
	}

	public int layoutGlobalY() {
		return (parent() == null ? 0 : Objects.requireNonNull(parent()).layoutGlobalY()) + layout.y();
	}

	public AbsWidget child(AbsWidget widget) {
		return child(widget, childCount());
	}

	public int childCount() {
		return children().size();
	}

	public float margin(WidgetEdge edge) {
		return edge.getMargin(this);
	}

	public AbsWidget margin(WidgetEdge edge, float value) {
		edge.setMargin(this, value);
		return this;
	}

	public float padding(WidgetEdge edge) {
		return edge.getPadding(this);
	}

	public AbsWidget padding(WidgetEdge edge, float value) {
		edge.setPadding(this, value);
		return this;
	}

	public AbsWidget border(WidgetEdge edge, float value) {
		edge.setBorder(this, value);
		return this;
	}

	public float border(WidgetEdge edge) {
		return edge.getBorder(this);
	}

	public float position(WidgetEdge edge) {
		return edge.getPosition(this);
	}

	public AbsWidget position(WidgetEdge edge, float value) {
		edge.setPosition(this, value);
		return this;
	}

	/**
	 * 描述了一个容器内的任何空间应如何沿主轴在其子代之间分配。在布置完它的子代后，容器将根据它的子代所指定的grow值来分配任何剩余的空间。
	 *
	 * @param value grow
	 *
	 * @return this
	 */
	public AbsWidget grow(float value) {
		YGNodeStyleSetFlexGrow(getYGNode(), value);
		return this;
	}

	public float grow() {
		return YGNodeStyleGetFlexGrow(getYGNode());
	}

	public AbsWidget dirty() {
		YGNodeMarkDirty(getYGNode());
		return this;
	}

	public double transformationX(double x) {
		return x - layout.x();
	}

	public double transformationY(double y) {
		return y - layout.y();
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {

	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (isMouseOver(mouseX, mouseY)) {
			for (AbsWidget widget : children()) {
				if (widget.mouseClicked(transformationX(mouseX), transformationY(mouseY), button)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (isMouseOver(mouseX, mouseY)) {
			for (AbsWidget widget : children()) {
				if (widget.mouseReleased(transformationX(mouseX), transformationY(mouseY), button)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		return false;
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		if (isMouseOver(mouseX, mouseY)) {
			for (AbsWidget widget : children()) {
				if (widget.mouseScrolled(transformationX(mouseX), transformationY(mouseY), amount)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		for (AbsWidget widget : children()) {
			if (widget.keyPressed(keyCode, scanCode, modifiers)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		for (AbsWidget widget : children()) {
			if (widget.keyReleased(keyCode, scanCode, modifiers)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean charTyped(char chr, int modifiers) {
		for (AbsWidget widget : children()) {
			if (widget.charTyped(chr, modifiers)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isMouseOver(double mouseX, double mouseY) {
		return mouseX >= this.layout.x() && mouseX <= layout.right() && mouseY >= this.layout.y() && mouseY <= layout.bottom();
	}

	protected final Layout layout = new Layout(this);
	protected static final class Layout {
		private final AbsWidget absWidget;

		public Layout(AbsWidget absWidget) {
			this.absWidget = absWidget;
		}

		public int left() {
			return x();
		}

		public int x() {
			return (int) YGNodeLayoutGetLeft(absWidget.getYGNode());
		}

		public int top() {
			return y();
		}

		public int y() {
			return (int) YGNodeLayoutGetTop(absWidget.getYGNode());
		}

		public int right() {
			return x() + width();
		}

		public int width() {
			return (int) YGNodeLayoutGetWidth(absWidget.getYGNode());
		}

		public int padding(WidgetEdge edge) {
			return (int) YGNodeLayoutGetPadding(absWidget.getYGNode(), edge.get());
		}

		public int margin(WidgetEdge edge) {
			return (int) YGNodeLayoutGetMargin(absWidget.getYGNode(), edge.get());
		}

		public int border(WidgetEdge edge) {
			return (int) YGNodeLayoutGetBorder(absWidget.getYGNode(), edge.get());
		}

		public int bottom() {
			return y() + height();
		}

		public int height() {
			return (int) YGNodeLayoutGetHeight(absWidget.getYGNode());
		}
	}
}
