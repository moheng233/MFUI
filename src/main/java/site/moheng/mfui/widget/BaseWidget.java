package site.moheng.mfui.widget;

import com.google.common.hash.HashCode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.util.yoga.Yoga.*;

public class BaseWidget implements IWidget {
	protected final MinecraftClient client;
	protected final ItemRenderer itemRenderer;
	protected final TextRenderer textRenderer;

	protected int hashcode;

	@Nullable
	protected ScreenWidget root = null;

	private final long ygNode = YGNodeNew();
	protected HashSet<IWidget> children = new HashSet<>();
	public IWidget parent;

	public BaseWidget() {
		long addr = ygNode;
		hashcode = HashCode.fromLong(addr).hashCode();
		IWidget.CLEANER.register(this, () -> YGNodeFree(addr));
		client = MinecraftClient.getInstance();
		itemRenderer = client.getItemRenderer();
		textRenderer = client.textRenderer;
	}

	@Override
	public IWidget parent(IWidget parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public IWidget parent() {
		return parent;
	}

	@Override
	public long getYGNode() {
		return ygNode;
	}

	@Override
	public ScreenWidget root() {
		assert root != null;
		return root;
	}

	@Override
	public void root(ScreenWidget root) {
		this.root = root;
	}

	@Override
	public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
		drawChildren(matrices, mouseX, mouseY, partialTicks, delta);
	}

	@Override
	public IWidget child(IWidget child, int index) {
		YGNodeInsertChild(getYGNode(), child.getYGNode(), childCount());
		children.add(child);
		child.parent(this);
		child.root(root());
		return this;
	}

	@Override
	public IWidget removeChild(IWidget child) {
		YGNodeRemoveChild(getYGNode(), child.getYGNode());
		children.remove(child);
		child.parent(null);
		child.root(null);
		return this;
	}

	@Override
	public Set<IWidget> children() {
		return children;
	}


	@Override
	public int hashCode() {
		return hashcode;
	}
}
