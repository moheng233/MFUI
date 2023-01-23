package site.moheng.mfui.widget;

import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.yoga.YGValue;
import site.moheng.mfui.widget.enums.*;


import java.lang.ref.Cleaner;
import java.util.Objects;
import java.util.Set;

import static org.lwjgl.util.yoga.Yoga.*;

public interface IWidget extends IWidgetHandler {
	YGValue VALUE = YGValue.create();
	Cleaner CLEANER = Cleaner.create();

	IWidget parent(@Nullable IWidget parent);

	@Nullable IWidget parent();

	default long getParentYGNode() {
		return YGNodeGetParent(getYGNode());
	}

	long getYGNode();

	void root(ScreenWidget root);

	@MustBeInvokedByOverriders
	default void tick() {
		for (IWidget widget : children()) {
			widget.tick();
		}
	}

	default void drawChildren(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		matrices.push();
		matrices.translate(layoutX(), layoutY(), 0);
		var tx = transformationX(mouseX);
		var ty = transformationY(mouseY);
		for (IWidget widget : children()) {
			widget.draw(matrices, (int) tx, (int) ty, partialTicks, delta);
		}
		matrices.pop();
	}

	default int layoutX() {
		return (int) YGNodeLayoutGetLeft(getYGNode());
	}

	default int layoutY() {
		return (int) YGNodeLayoutGetTop(getYGNode());
	}

	Set<IWidget> children();

	void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta);

	default boolean isFocus() {
		return root().focus.equals(this);
	}

	ScreenWidget root();

	default int layoutGlobalX() {
		return (parent() == null ? 0 : Objects.requireNonNull(parent()).layoutGlobalX()) + layoutX();
	}

	default int layoutGlobalY() {
		return (parent() == null ? 0 : Objects.requireNonNull(parent()).layoutGlobalY()) + layoutY();
	}

	default IWidget child(IWidget widget) {
		return child(widget, childCount());
	}

	IWidget child(IWidget widget, int index);

	default int childCount() {
		return children().size();
	}

	IWidget removeChild(IWidget widget);

	default float margin(WidgetEdge edge) {
		return edge.getMargin(this);
	}

	default IWidget margin(WidgetEdge edge, float value) {
		edge.setMargin(this, value);
		return this;
	}

	default float padding(WidgetEdge edge) {
		return edge.getPadding(this);
	}

	default IWidget padding(WidgetEdge edge, float value) {
		edge.setPadding(this, value);
		return this;
	}

	default float width() {
		VALUE.clear();
		return YGNodeStyleGetWidth(getYGNode(), VALUE).value();
	}

	default WidgetDisplay flex() {
		return WidgetDisplay.getDisplay(this);
	}

	default IWidget flex(WidgetDisplay type) {
		type.setDisplay(this);
		return this;
	}

	default WidgetJustify justifyContent() {
		return WidgetJustify.getJustifyContent(this);
	}

	default IWidget justifyContent(WidgetJustify justify) {
		justify.setJustifyContent(this);
		return this;
	}

	default WidgetAlign alignContent() {
		return WidgetAlign.getAlignContent(this);
	}

	default IWidget alignContent(WidgetAlign align) {
		align.setAlignContent(this);
		return this;
	}

	default WidgetAlign alignSelf() {
		return WidgetAlign.getAlignSelf(this);
	}

	default IWidget alignSelf(WidgetAlign align) {
		align.setAlignSelf(this);
		return this;
	}

	default WidgetAlign alignItems() {
		return WidgetAlign.getAlignItems(this);
	}

	default IWidget alignItems(WidgetAlign align) {
		align.setAlignItems(this);
		return this;
	}

	default WidgetFlexDirection flexDirection() {
		return WidgetFlexDirection.getFlexDirect(this);
	}

	default IWidget flexDirection(WidgetFlexDirection direction) {
		direction.setFlexDirection(this);
		return this;
	}

	default float position(WidgetEdge edge) {
		return edge.getPosition(this);
	}

	default IWidget position(WidgetEdge edge, float value) {
		edge.setPosition(this, value);
		return this;
	}

	default IWidget width(float w) {
		YGNodeStyleSetWidth(getYGNode(), w);
		return this;
	}

	default IWidget widthAuto() {
		YGNodeStyleSetWidthAuto(getYGNode());
		return this;
	}

	default IWidget widthPercent(float w) {
		YGNodeStyleSetWidthPercent(getYGNode(), w);
		return this;
	}

	default float height() {
		VALUE.clear();
		return YGNodeStyleGetHeight(getYGNode(), VALUE).value();
	}

	default IWidget height(float h) {
		YGNodeStyleSetHeight(getYGNode(), h);
		return this;
	}

	default IWidget heightAuto() {
		YGNodeStyleSetHeightAuto(getYGNode());
		return this;
	}

	default IWidget heightPercent(float h) {
		YGNodeStyleSetHeightPercent(getYGNode(), h);
		return this;
	}

	default int layoutLeft() {
		return layoutX();
	}

	default int layoutTop() {
		return layoutY();
	}

	default int layoutRight() {
		return (int) YGNodeLayoutGetRight(getYGNode());
	}

	default int layoutBottom() {
		return (int) YGNodeLayoutGetBottom(getYGNode());
	}

	default int layoutWidth() {
		return (int) YGNodeLayoutGetWidth(getYGNode());
	}

	default int layoutHeight() {
		return (int) YGNodeLayoutGetHeight(getYGNode());
	}

	default IWidget dirty() {
		YGNodeMarkDirty(getYGNode());
		return this;
	}

	default double transformationX(double x) {
		return x - layoutX();
	}

	default double transformationY(double y) {
		return y - layoutY();
	}

	@Override
	default void mouseMoved(double mouseX, double mouseY) {

	}

	@Override
	default boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (isMouseOver(mouseX, mouseY)) {
			for (IWidget widget : children()) {
				if (widget.mouseClicked(transformationX(mouseX), transformationY(mouseY), button)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	default boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (isMouseOver(mouseX, mouseY)) {
			for (IWidget widget : children()) {
				if (widget.mouseReleased(transformationX(mouseX), transformationY(mouseY), button)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	default boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		return false;
	}

	@Override
	default boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		if(isMouseOver(mouseX, mouseY)) {
			for (IWidget widget: children()) {
				if(widget.mouseScrolled(transformationX(mouseX), transformationY(mouseY), amount)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		for (IWidget widget: children()) {
			if(widget.keyPressed(keyCode, scanCode, modifiers)) {
				return true;
			}
		}
		return false;
	}

	@Override
	default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		for (IWidget widget: children()) {
			if(widget.keyReleased(keyCode, scanCode, modifiers)) {
				return true;
			}
		}
		return false;
	}

	@Override
	default boolean charTyped(char chr, int modifiers) {
		for (IWidget widget: children()) {
			if(widget.charTyped(chr, modifiers)) {
				return true;
			}
		}
		return false;
	}

	@Override
	default boolean isMouseOver(double mouseX, double mouseY) {
		return mouseX >= this.layoutX() && mouseX <= layoutX() + layoutWidth() && mouseY >= this.layoutY() && mouseY <= layoutY() + layoutHeight();
	}
}
