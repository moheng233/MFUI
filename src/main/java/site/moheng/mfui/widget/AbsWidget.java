package site.moheng.mfui.widget;

import com.google.common.hash.HashCode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.yoga.YGValue;
import site.moheng.mfui.binding.GetterWidgetAttribute;
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
		matrices.translate(layoutX(), layoutY(), 0);
		var tx = transformationX(mouseX);
		var ty = transformationY(mouseY);
		for (AbsWidget widget : children()) {
			widget.draw(matrices, (int) tx, (int) ty, partialTicks, delta);
		}
		matrices.pop();
	}

	public boolean isFocused() {
		if (root() == null) {
			return false;
		}
		return root().isFocused(this);
	}

	public int layoutGlobalX() {
		return (parent() == null ? 0 : Objects.requireNonNull(parent()).layoutGlobalX()) + layoutX();
	}

	public int layoutGlobalY() {
		return (parent() == null ? 0 : Objects.requireNonNull(parent()).layoutGlobalY()) + layoutY();
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

	public float width() {
		VALUE.clear();
		return YGNodeStyleGetWidth(getYGNode(), VALUE).value();
	}

	public WidgetDisplay flex() {
		return WidgetDisplay.getDisplay(this);
	}

	public AbsWidget flex(WidgetDisplay type) {
		type.setDisplay(this);
		return this;
	}

	public WidgetJustify justifyContent() {
		return WidgetJustify.getJustifyContent(this);
	}

	public AbsWidget justifyContent(WidgetJustify justify) {
		justify.setJustifyContent(this);
		return this;
	}

	public WidgetAlign alignContent() {
		return WidgetAlign.getAlignContent(this);
	}

	public AbsWidget alignContent(WidgetAlign align) {
		align.setAlignContent(this);
		return this;
	}

	public WidgetAlign alignSelf() {
		return WidgetAlign.getAlignSelf(this);
	}

	public AbsWidget alignSelf(WidgetAlign align) {
		align.setAlignSelf(this);
		return this;
	}

	public WidgetAlign alignItems() {
		return WidgetAlign.getAlignItems(this);
	}

	public AbsWidget alignItems(WidgetAlign align) {
		align.setAlignItems(this);
		return this;
	}

	public WidgetFlexDirection flexDirection() {
		return WidgetFlexDirection.getFlexDirect(this);
	}

	public AbsWidget flexDirection(WidgetFlexDirection direction) {
		direction.setFlexDirection(this);
		return this;
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

	public int layoutLeft() {
		return layoutX();
	}

	public int layoutX() {
		return (int) YGNodeLayoutGetLeft(getYGNode());
	}

	public int layoutTop() {
		return layoutY();
	}

	public int layoutY() {
		return (int) YGNodeLayoutGetTop(getYGNode());
	}

	public int layoutRight() {
		return layoutX() + layoutWidth();
	}

	public int layoutWidth() {
		return (int) YGNodeLayoutGetWidth(getYGNode());
	}

	public int layoutPadding(WidgetEdge edge) {
		return (int) YGNodeLayoutGetPadding(getYGNode(), edge.get());
	}

	public int layoutMargin(WidgetEdge edge) {
		return (int) YGNodeLayoutGetMargin(getYGNode(), edge.get());
	}

	public int layoutBorder(WidgetEdge edge) {
		return (int) YGNodeLayoutGetBorder(getYGNode(), edge.get());
	}

	public int layoutBottom() {
		return layoutY() + layoutHeight();
	}

	public int layoutHeight() {
		return (int) YGNodeLayoutGetHeight(getYGNode());
	}

	public AbsWidget dirty() {
		YGNodeMarkDirty(getYGNode());
		return this;
	}

	public double transformationX(double x) {
		return x - layoutX();
	}

	public double transformationY(double y) {
		return y - layoutY();
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
		return mouseX >= this.layoutX() && mouseX <= layoutX() + layoutWidth() && mouseY >= this.layoutY() && mouseY <= layoutY() + layoutHeight();
	}
}
