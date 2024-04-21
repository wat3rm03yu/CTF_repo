package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.widget.ListViewAutoScrollHelper;
import java.lang.reflect.Field;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DropDownListView extends ListView {
    public static final int INVALID_POSITION = -1;
    public static final int NO_POSITION = -1;
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private Field mIsChildViewEnabled;
    private boolean mListSelectionHidden;
    private int mMotionPosition;
    ResolveHoverRunnable mResolveHoverRunnable;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding;
    private int mSelectionLeftPadding;
    private int mSelectionRightPadding;
    private int mSelectionTopPadding;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DropDownListView(Context context, boolean hijackFocus) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.mSelectorRect = new Rect();
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mHijackFocus = hijackFocus;
        setCacheColorHint(0);
        try {
            this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.mIsChildViewEnabled.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable sel) {
        this.mSelector = sel != null ? new GateKeeperDrawable(sel) : null;
        super.setSelector(this.mSelector);
        Rect padding = new Rect();
        if (sel != null) {
            sel.getPadding(padding);
        }
        this.mSelectionLeftPadding = padding.left;
        this.mSelectionTopPadding = padding.top;
        this.mSelectionRightPadding = padding.right;
        this.mSelectionBottomPadding = padding.bottom;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.mResolveHoverRunnable != null) {
            return;
        }
        super.drawableStateChanged();
        setSelectorEnabled(true);
        updateSelectorStateCompat();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        drawSelectorCompat(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mMotionPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
        }
        ResolveHoverRunnable resolveHoverRunnable = this.mResolveHoverRunnable;
        if (resolveHoverRunnable != null) {
            resolveHoverRunnable.cancel();
        }
        return super.onTouchEvent(ev);
    }

    public int lookForSelectablePosition(int position, boolean lookDown) {
        int position2;
        ListAdapter adapter = getAdapter();
        if (adapter == null || isInTouchMode()) {
            return -1;
        }
        int count = adapter.getCount();
        if (!getAdapter().areAllItemsEnabled()) {
            if (lookDown) {
                position2 = Math.max(0, position);
                while (position2 < count && !adapter.isEnabled(position2)) {
                    position2++;
                }
            } else {
                position2 = Math.min(position, count - 1);
                while (position2 >= 0 && !adapter.isEnabled(position2)) {
                    position2--;
                }
            }
            if (position2 < 0 || position2 >= count) {
                return -1;
            }
            return position2;
        } else if (position < 0 || position >= count) {
            return -1;
        } else {
            return position;
        }
    }

    public int measureHeightOfChildrenCompat(int widthMeasureSpec, int startPosition, int endPosition, int maxHeight, int disallowPartialChildPosition) {
        ViewGroup.LayoutParams childLp;
        int paddingTop = getListPaddingTop();
        int paddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int reportedDividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return paddingTop + paddingBottom;
        }
        int returnedHeight = paddingTop + paddingBottom;
        int dividerHeight = (reportedDividerHeight <= 0 || divider == null) ? 0 : reportedDividerHeight;
        View child = null;
        int count = adapter.getCount();
        int viewType = 0;
        int prevHeightWithoutPartialChild = 0;
        int returnedHeight2 = returnedHeight;
        int returnedHeight3 = 0;
        while (returnedHeight3 < count) {
            int paddingTop2 = paddingTop;
            int newType = adapter.getItemViewType(returnedHeight3);
            if (newType != viewType) {
                child = null;
                viewType = newType;
            }
            child = adapter.getView(returnedHeight3, child, this);
            ViewGroup.LayoutParams childLp2 = child.getLayoutParams();
            if (childLp2 != null) {
                childLp = childLp2;
            } else {
                childLp = generateDefaultLayoutParams();
                child.setLayoutParams(childLp);
            }
            int paddingBottom2 = paddingBottom;
            int heightMeasureSpec = childLp.height > 0 ? View.MeasureSpec.makeMeasureSpec(childLp.height, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0);
            child.measure(widthMeasureSpec, heightMeasureSpec);
            child.forceLayout();
            if (returnedHeight3 > 0) {
                returnedHeight2 += dividerHeight;
            }
            returnedHeight2 += child.getMeasuredHeight();
            if (returnedHeight2 >= maxHeight) {
                return (disallowPartialChildPosition < 0 || returnedHeight3 <= disallowPartialChildPosition || prevHeightWithoutPartialChild <= 0 || returnedHeight2 == maxHeight) ? maxHeight : prevHeightWithoutPartialChild;
            }
            if (disallowPartialChildPosition >= 0 && returnedHeight3 >= disallowPartialChildPosition) {
                prevHeightWithoutPartialChild = returnedHeight2;
            }
            returnedHeight3++;
            paddingTop = paddingTop2;
            paddingBottom = paddingBottom2;
        }
        return returnedHeight2;
    }

    private void setSelectorEnabled(boolean enabled) {
        GateKeeperDrawable gateKeeperDrawable = this.mSelector;
        if (gateKeeperDrawable != null) {
            gateKeeperDrawable.setEnabled(enabled);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class GateKeeperDrawable extends DrawableWrapper {
        private boolean mEnabled;

        GateKeeperDrawable(Drawable drawable) {
            super(drawable);
            this.mEnabled = true;
        }

        void setEnabled(boolean enabled) {
            this.mEnabled = enabled;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public boolean setState(int[] stateSet) {
            if (this.mEnabled) {
                return super.setState(stateSet);
            }
            return false;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.mEnabled) {
                super.draw(canvas);
            }
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void setHotspot(float x, float y) {
            if (this.mEnabled) {
                super.setHotspot(x, y);
            }
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void setHotspotBounds(int left, int top, int right, int bottom) {
            if (this.mEnabled) {
                super.setHotspotBounds(left, top, right, bottom);
            }
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public boolean setVisible(boolean visible, boolean restart) {
            if (this.mEnabled) {
                return super.setVisible(visible, restart);
            }
            return false;
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent ev) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(ev);
        }
        int action = ev.getActionMasked();
        if (action == 10 && this.mResolveHoverRunnable == null) {
            this.mResolveHoverRunnable = new ResolveHoverRunnable();
            this.mResolveHoverRunnable.post();
        }
        boolean handled = super.onHoverEvent(ev);
        if (action == 9 || action == 7) {
            int position = pointToPosition((int) ev.getX(), (int) ev.getY());
            if (position != -1 && position != getSelectedItemPosition()) {
                View hoveredItem = getChildAt(position - getFirstVisiblePosition());
                if (hoveredItem.isEnabled()) {
                    setSelectionFromTop(position, hoveredItem.getTop() - getTop());
                }
                updateSelectorStateCompat();
            }
        } else {
            setSelection(-1);
        }
        return handled;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mResolveHoverRunnable = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onForwardedEvent(android.view.MotionEvent r12, int r13) {
        /*
            r11 = this;
            r0 = 1
            r1 = 0
            int r2 = r12.getActionMasked()
            r3 = 1
            if (r2 == r3) goto L12
            r4 = 2
            if (r2 == r4) goto L13
            r4 = 3
            if (r2 == r4) goto L10
            goto L43
        L10:
            r0 = 0
            goto L43
        L12:
            r0 = 0
        L13:
            int r4 = r12.findPointerIndex(r13)
            if (r4 >= 0) goto L1b
            r0 = 0
            goto L43
        L1b:
            float r5 = r12.getX(r4)
            int r5 = (int) r5
            float r6 = r12.getY(r4)
            int r6 = (int) r6
            int r7 = r11.pointToPosition(r5, r6)
            r8 = -1
            if (r7 != r8) goto L2e
            r1 = 1
            goto L43
        L2e:
            int r8 = r11.getFirstVisiblePosition()
            int r8 = r7 - r8
            android.view.View r8 = r11.getChildAt(r8)
            float r9 = (float) r5
            float r10 = (float) r6
            r11.setPressedItem(r8, r7, r9, r10)
            r0 = 1
            if (r2 != r3) goto L43
            r11.clickPressedItem(r8, r7)
        L43:
            if (r0 == 0) goto L47
            if (r1 == 0) goto L4a
        L47:
            r11.clearPressedItem()
        L4a:
            if (r0 == 0) goto L62
            androidx.core.widget.ListViewAutoScrollHelper r4 = r11.mScrollHelper
            if (r4 != 0) goto L57
            androidx.core.widget.ListViewAutoScrollHelper r4 = new androidx.core.widget.ListViewAutoScrollHelper
            r4.<init>(r11)
            r11.mScrollHelper = r4
        L57:
            androidx.core.widget.ListViewAutoScrollHelper r4 = r11.mScrollHelper
            r4.setEnabled(r3)
            androidx.core.widget.ListViewAutoScrollHelper r3 = r11.mScrollHelper
            r3.onTouch(r11, r12)
            goto L6a
        L62:
            androidx.core.widget.ListViewAutoScrollHelper r3 = r11.mScrollHelper
            if (r3 == 0) goto L6a
            r4 = 0
            r3.setEnabled(r4)
        L6a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.DropDownListView.onForwardedEvent(android.view.MotionEvent, int):boolean");
    }

    private void clickPressedItem(View child, int position) {
        long id = getItemIdAtPosition(position);
        performItemClick(child, position, id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean hideListSelection) {
        this.mListSelectionHidden = hideListSelection;
    }

    private void updateSelectorStateCompat() {
        Drawable selector = getSelector();
        if (selector != null && touchModeDrawsInPressedStateCompat() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    private void drawSelectorCompat(Canvas canvas) {
        Drawable selector;
        if (!this.mSelectorRect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.mSelectorRect);
            selector.draw(canvas);
        }
    }

    private void positionSelectorLikeTouchCompat(int position, View sel, float x, float y) {
        positionSelectorLikeFocusCompat(position, sel);
        Drawable selector = getSelector();
        if (selector != null && position != -1) {
            DrawableCompat.setHotspot(selector, x, y);
        }
    }

    private void positionSelectorLikeFocusCompat(int position, View sel) {
        Drawable selector = getSelector();
        boolean manageState = (selector == null || position == -1) ? false : true;
        if (manageState) {
            selector.setVisible(false, false);
        }
        positionSelectorCompat(position, sel);
        if (manageState) {
            Rect bounds = this.mSelectorRect;
            float x = bounds.exactCenterX();
            float y = bounds.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            DrawableCompat.setHotspot(selector, x, y);
        }
    }

    private void positionSelectorCompat(int position, View sel) {
        Rect selectorRect = this.mSelectorRect;
        selectorRect.set(sel.getLeft(), sel.getTop(), sel.getRight(), sel.getBottom());
        selectorRect.left -= this.mSelectionLeftPadding;
        selectorRect.top -= this.mSelectionTopPadding;
        selectorRect.right += this.mSelectionRightPadding;
        selectorRect.bottom += this.mSelectionBottomPadding;
        try {
            boolean isChildViewEnabled = this.mIsChildViewEnabled.getBoolean(this);
            if (sel.isEnabled() != isChildViewEnabled) {
                this.mIsChildViewEnabled.set(this, Boolean.valueOf(!isChildViewEnabled));
                if (position != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        setPressed(false);
        drawableStateChanged();
        View motionView = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
        if (motionView != null) {
            motionView.setPressed(false);
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mClickAnimation;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
            this.mClickAnimation = null;
        }
    }

    private void setPressedItem(View child, int position, float x, float y) {
        View motionView;
        this.mDrawsInPressedState = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(x, y);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i = this.mMotionPosition;
        if (i != -1 && (motionView = getChildAt(i - getFirstVisiblePosition())) != null && motionView != child && motionView.isPressed()) {
            motionView.setPressed(false);
        }
        this.mMotionPosition = position;
        float childX = x - child.getLeft();
        float childY = y - child.getTop();
        if (Build.VERSION.SDK_INT >= 21) {
            child.drawableHotspotChanged(childX, childY);
        }
        if (!child.isPressed()) {
            child.setPressed(true);
        }
        positionSelectorLikeTouchCompat(position, child, x, y);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private boolean touchModeDrawsInPressedStateCompat() {
        return this.mDrawsInPressedState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ResolveHoverRunnable implements Runnable {
        ResolveHoverRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView dropDownListView = DropDownListView.this;
            dropDownListView.mResolveHoverRunnable = null;
            dropDownListView.drawableStateChanged();
        }

        public void cancel() {
            DropDownListView dropDownListView = DropDownListView.this;
            dropDownListView.mResolveHoverRunnable = null;
            dropDownListView.removeCallbacks(this);
        }

        public void post() {
            DropDownListView.this.post(this);
        }
    }
}