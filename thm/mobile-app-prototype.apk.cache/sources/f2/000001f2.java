package androidx.constraintlayout.solver.widgets;

import androidx.appcompat.widget.ActivityChooserView;
import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import com.example.ctf1.BuildConfig;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ConstraintWidget {
    protected static final int ANCHOR_BASELINE = 4;
    protected static final int ANCHOR_BOTTOM = 3;
    protected static final int ANCHOR_LEFT = 0;
    protected static final int ANCHOR_RIGHT = 1;
    protected static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintWidgetGroup mBelongingGroup;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    float mMatchConstraintPercentHeight;
    float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    boolean mOptimizerMeasured;
    ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    int[] mResolvedMatchConstraintDefault;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    /* loaded from: classes.dex */
    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int maxWidth) {
        this.mMaxDimension[0] = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.mMaxDimension[1] = maxHeight;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMaxHeight = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        ResolutionDimension resolutionDimension = this.mResolutionWidth;
        if (resolutionDimension != null) {
            resolutionDimension.reset();
        }
        ResolutionDimension resolutionDimension2 = this.mResolutionHeight;
        if (resolutionDimension2 != null) {
            resolutionDimension2.reset();
        }
        this.mBelongingGroup = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int optimizationLevel) {
        Optimizer.analyze(optimizationLevel, this);
    }

    public void resolve() {
    }

    public boolean isFullyResolved() {
        return this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int x, int y, int width, int height) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = x;
        this.mY = y;
        this.mWidth = width;
        this.mHeight = height;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int width, int height) {
        this(0, 0, width, height);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        ConstraintWidget constraintWidget;
        return (this instanceof ConstraintWidgetContainer) && ((constraintWidget = this.mParent) == null || !(constraintWidget instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget widget = getParent();
        if (widget == null) {
            return false;
        }
        while (widget != null) {
            if (widget instanceof ConstraintWidgetContainer) {
                return true;
            }
            widget = widget.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget widget) {
        ConstraintWidget parent = getParent();
        if (parent == widget) {
            return true;
        }
        if (parent == widget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == widget || parent == widget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget root = this;
        while (root.getParent() != null) {
            root = root.getParent();
        }
        if (root instanceof WidgetContainer) {
            return (WidgetContainer) root;
        }
        return null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget widget) {
        this.mParent = widget;
    }

    public void setWidthWrapContent(boolean widthWrapContent) {
        this.mIsWidthWrapContent = widthWrapContent;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean heightWrapContent) {
        this.mIsHeightWrapContent = heightWrapContent;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget target, float angle, int radius) {
        immediateConnect(ConstraintAnchor.Type.CENTER, target, ConstraintAnchor.Type.CENTER, radius, 0);
        this.mCircleConstraintAngle = angle;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public void setVisibility(int visibility) {
        this.mVisibility = visibility;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String name) {
        this.mDebugName = name;
    }

    public void setDebugSolverName(LinearSystem system, String name) {
        this.mDebugName = name;
        SolverVariable left = system.createObjectVariable(this.mLeft);
        SolverVariable top = system.createObjectVariable(this.mTop);
        SolverVariable right = system.createObjectVariable(this.mRight);
        SolverVariable bottom = system.createObjectVariable(this.mBottom);
        left.setName(name + ".left");
        top.setName(name + ".top");
        right.setName(name + ".right");
        bottom.setName(name + ".bottom");
        if (this.mBaselineDistance > 0) {
            SolverVariable baseline = system.createObjectVariable(this.mBaseline);
            baseline.setName(name + ".baseline");
        }
    }

    public void createObjectVariables(LinearSystem system) {
        system.createObjectVariable(this.mLeft);
        system.createObjectVariable(this.mTop);
        system.createObjectVariable(this.mRight);
        system.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            system.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = this.mType;
        String str3 = BuildConfig.FLAVOR;
        if (str2 != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = BuildConfig.FLAVOR;
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str3 = "id: " + this.mDebugName + " ";
        }
        sb.append(str3);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(") wrap: (");
        sb.append(this.mWrapWidth);
        sb.append(" x ");
        sb.append(this.mWrapHeight);
        sb.append(")");
        return sb.toString();
    }

    int getInternalDrawX() {
        return this.mDrawX;
    }

    int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int w;
        int w2 = this.mWidth;
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                w = Math.max(this.mMatchConstraintMinWidth, w2);
            } else if (this.mMatchConstraintMinWidth > 0) {
                w = this.mMatchConstraintMinWidth;
                this.mWidth = w;
            } else {
                w = 0;
            }
            int i = this.mMatchConstraintMaxWidth;
            if (i > 0 && i < w) {
                return this.mMatchConstraintMaxWidth;
            }
            return w;
        }
        return w2;
    }

    public int getOptimizerWrapHeight() {
        int h;
        int h2 = this.mHeight;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                h = Math.max(this.mMatchConstraintMinHeight, h2);
            } else if (this.mMatchConstraintMinHeight > 0) {
                h = this.mMatchConstraintMinHeight;
                this.mHeight = h;
            } else {
                h = 0;
            }
            int i = this.mMatchConstraintMaxHeight;
            if (i > 0 && i < h) {
                return this.mMatchConstraintMaxHeight;
            }
            return h;
        }
        return h2;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getLength(int orientation) {
        if (orientation == 0) {
            return getWidth();
        }
        if (orientation == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int orientation) {
        if (orientation == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (orientation == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int x) {
        this.mX = x;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public void setOrigin(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    public void setOffset(int x, int y) {
        this.mOffsetX = x;
        this.mOffsetY = y;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int goneMargin) {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[type.ordinal()];
        if (i == 1) {
            this.mLeft.mGoneMargin = goneMargin;
        } else if (i == 2) {
            this.mTop.mGoneMargin = goneMargin;
        } else if (i == 3) {
            this.mRight.mGoneMargin = goneMargin;
        } else if (i == 4) {
            this.mBottom.mGoneMargin = goneMargin;
        }
    }

    public void updateDrawPosition() {
        int left = this.mX;
        int top = this.mY;
        int right = this.mX + this.mWidth;
        int bottom = this.mY + this.mHeight;
        this.mDrawX = left;
        this.mDrawY = top;
        this.mDrawWidth = right - left;
        this.mDrawHeight = bottom - top;
    }

    public void forceUpdateDrawPosition() {
        int left = this.mX;
        int top = this.mY;
        int right = this.mX + this.mWidth;
        int bottom = this.mY + this.mHeight;
        this.mDrawX = left;
        this.mDrawY = top;
        this.mDrawWidth = right - left;
        this.mDrawHeight = bottom - top;
    }

    public void setDrawOrigin(int x, int y) {
        this.mDrawX = x - this.mOffsetX;
        this.mDrawY = y - this.mOffsetY;
        this.mX = this.mDrawX;
        this.mY = this.mDrawY;
    }

    public void setDrawX(int x) {
        this.mDrawX = x - this.mOffsetX;
        this.mX = this.mDrawX;
    }

    public void setDrawY(int y) {
        this.mDrawY = y - this.mOffsetY;
        this.mY = this.mDrawY;
    }

    public void setDrawWidth(int drawWidth) {
        this.mDrawWidth = drawWidth;
    }

    public void setDrawHeight(int drawHeight) {
        this.mDrawHeight = drawHeight;
    }

    public void setWidth(int w) {
        this.mWidth = w;
        int i = this.mWidth;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int h) {
        this.mHeight = h;
        int i = this.mHeight;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setLength(int length, int orientation) {
        if (orientation == 0) {
            setWidth(length);
        } else if (orientation == 1) {
            setHeight(length);
        }
    }

    public void setHorizontalMatchStyle(int horizontalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultWidth = horizontalMatchStyle;
        this.mMatchConstraintMinWidth = min;
        this.mMatchConstraintMaxWidth = max;
        this.mMatchConstraintPercentWidth = percent;
        if (percent < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int verticalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultHeight = verticalMatchStyle;
        this.mMatchConstraintMinHeight = min;
        this.mMatchConstraintMaxHeight = max;
        this.mMatchConstraintPercentHeight = percent;
        if (percent < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setDimensionRatio(String ratio) {
        int commaIndex;
        if (ratio == null || ratio.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int dimensionRatioSide = -1;
        float dimensionRatio = 0.0f;
        int len = ratio.length();
        int commaIndex2 = ratio.indexOf(44);
        if (commaIndex2 > 0 && commaIndex2 < len - 1) {
            String dimension = ratio.substring(0, commaIndex2);
            if (dimension.equalsIgnoreCase("W")) {
                dimensionRatioSide = 0;
            } else if (dimension.equalsIgnoreCase("H")) {
                dimensionRatioSide = 1;
            }
            commaIndex = commaIndex2 + 1;
        } else {
            commaIndex = 0;
        }
        int colonIndex = ratio.indexOf(58);
        if (colonIndex >= 0 && colonIndex < len - 1) {
            String nominator = ratio.substring(commaIndex, colonIndex);
            String denominator = ratio.substring(colonIndex + 1);
            if (nominator.length() > 0 && denominator.length() > 0) {
                try {
                    float nominatorValue = Float.parseFloat(nominator);
                    float denominatorValue = Float.parseFloat(denominator);
                    if (nominatorValue > 0.0f && denominatorValue > 0.0f) {
                        dimensionRatio = dimensionRatioSide == 1 ? Math.abs(denominatorValue / nominatorValue) : Math.abs(nominatorValue / denominatorValue);
                    }
                } catch (NumberFormatException e) {
                }
            }
        } else {
            String r = ratio.substring(commaIndex);
            if (r.length() > 0) {
                try {
                    dimensionRatio = Float.parseFloat(r);
                } catch (NumberFormatException e2) {
                }
            }
        }
        if (dimensionRatio > 0.0f) {
            this.mDimensionRatio = dimensionRatio;
            this.mDimensionRatioSide = dimensionRatioSide;
        }
    }

    public void setDimensionRatio(float ratio, int dimensionRatioSide) {
        this.mDimensionRatio = ratio;
        this.mDimensionRatioSide = dimensionRatioSide;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float horizontalBiasPercent) {
        this.mHorizontalBiasPercent = horizontalBiasPercent;
    }

    public void setVerticalBiasPercent(float verticalBiasPercent) {
        this.mVerticalBiasPercent = verticalBiasPercent;
    }

    public void setMinWidth(int w) {
        if (w < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = w;
        }
    }

    public void setMinHeight(int h) {
        if (h < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = h;
        }
    }

    public void setWrapWidth(int w) {
        this.mWrapWidth = w;
    }

    public void setWrapHeight(int h) {
        this.mWrapHeight = h;
    }

    public void setDimension(int w, int h) {
        this.mWidth = w;
        int i = this.mWidth;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
        this.mHeight = h;
        int i3 = this.mHeight;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    public void setFrame(int left, int top, int right, int bottom) {
        int w = right - left;
        int h = bottom - top;
        this.mX = left;
        this.mY = top;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && w < this.mWidth) {
            w = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && h < this.mHeight) {
            h = this.mHeight;
        }
        this.mWidth = w;
        this.mHeight = h;
        int i = this.mHeight;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
        int i3 = this.mWidth;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
        this.mOptimizerMeasured = true;
    }

    public void setFrame(int start, int end, int orientation) {
        if (orientation == 0) {
            setHorizontalDimension(start, end);
        } else if (orientation == 1) {
            setVerticalDimension(start, end);
        }
        this.mOptimizerMeasured = true;
    }

    public void setHorizontalDimension(int left, int right) {
        this.mX = left;
        this.mWidth = right - left;
        int i = this.mWidth;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setVerticalDimension(int top, int bottom) {
        this.mY = top;
        this.mHeight = bottom - top;
        int i = this.mHeight;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRelativePositioning(int orientation) {
        if (orientation == 0) {
            return this.mRelX;
        }
        if (orientation == 1) {
            return this.mRelY;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRelativePositioning(int offset, int orientation) {
        if (orientation == 0) {
            this.mRelX = offset;
        } else if (orientation == 1) {
            this.mRelY = offset;
        }
    }

    public void setBaselineDistance(int baseline) {
        this.mBaselineDistance = baseline;
    }

    public void setCompanionWidget(Object companion) {
        this.mCompanionWidget = companion;
    }

    public void setContainerItemSkip(int skip) {
        if (skip >= 0) {
            this.mContainerItemSkip = skip;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float horizontalWeight) {
        this.mWeight[0] = horizontalWeight;
    }

    public void setVerticalWeight(float verticalWeight) {
        this.mWeight[1] = verticalWeight;
    }

    public void setHorizontalChainStyle(int horizontalChainStyle) {
        this.mHorizontalChainStyle = horizontalChainStyle;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int verticalChainStyle) {
        this.mVerticalChainStyle = verticalChainStyle;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void connectedTo(ConstraintWidget source) {
    }

    public void immediateConnect(ConstraintAnchor.Type startType, ConstraintWidget target, ConstraintAnchor.Type endType, int margin, int goneMargin) {
        ConstraintAnchor startAnchor = getAnchor(startType);
        ConstraintAnchor endAnchor = target.getAnchor(endType);
        startAnchor.connect(endAnchor, margin, goneMargin, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor from, ConstraintAnchor to, int margin, int creator) {
        connect(from, to, margin, ConstraintAnchor.Strength.STRONG, creator);
    }

    public void connect(ConstraintAnchor from, ConstraintAnchor to, int margin) {
        connect(from, to, margin, ConstraintAnchor.Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor from, ConstraintAnchor to, int margin, ConstraintAnchor.Strength strength, int creator) {
        if (from.getOwner() == this) {
            connect(from.getType(), to.getOwner(), to.getType(), margin, strength, creator);
        }
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo, int margin) {
        connect(constraintFrom, target, constraintTo, margin, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo) {
        connect(constraintFrom, target, constraintTo, 0, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo, int margin, ConstraintAnchor.Strength strength) {
        connect(constraintFrom, target, constraintTo, margin, strength, 0);
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo, int margin, ConstraintAnchor.Strength strength, int creator) {
        int margin2;
        if (constraintFrom == ConstraintAnchor.Type.CENTER) {
            if (constraintTo == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean centerX = false;
                boolean centerY = false;
                if ((left == null || !left.isConnected()) && (right == null || !right.isConnected())) {
                    connect(ConstraintAnchor.Type.LEFT, target, ConstraintAnchor.Type.LEFT, 0, strength, creator);
                    connect(ConstraintAnchor.Type.RIGHT, target, ConstraintAnchor.Type.RIGHT, 0, strength, creator);
                    centerX = true;
                }
                if ((top == null || !top.isConnected()) && (bottom == null || !bottom.isConnected())) {
                    connect(ConstraintAnchor.Type.TOP, target, ConstraintAnchor.Type.TOP, 0, strength, creator);
                    connect(ConstraintAnchor.Type.BOTTOM, target, ConstraintAnchor.Type.BOTTOM, 0, strength, creator);
                    centerY = true;
                }
                if (centerX && centerY) {
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(ConstraintAnchor.Type.CENTER), 0, creator);
                } else if (centerX) {
                    getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_X), 0, creator);
                } else if (centerY) {
                    getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0, creator);
                }
            } else if (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT) {
                connect(ConstraintAnchor.Type.LEFT, target, constraintTo, 0, strength, creator);
                connect(ConstraintAnchor.Type.RIGHT, target, constraintTo, 0, strength, creator);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0, creator);
            } else if (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM) {
                connect(ConstraintAnchor.Type.TOP, target, constraintTo, 0, strength, creator);
                connect(ConstraintAnchor.Type.BOTTOM, target, constraintTo, 0, strength, creator);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0, creator);
            }
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor left2 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor targetAnchor = target.getAnchor(constraintTo);
            ConstraintAnchor right2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            left2.connect(targetAnchor, 0, creator);
            right2.connect(targetAnchor, 0, creator);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(targetAnchor, 0, creator);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor targetAnchor2 = target.getAnchor(constraintTo);
            getAnchor(ConstraintAnchor.Type.TOP).connect(targetAnchor2, 0, creator);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(targetAnchor2, 0, creator);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(targetAnchor2, 0, creator);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && constraintTo == ConstraintAnchor.Type.CENTER_X) {
            ConstraintAnchor left3 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor leftTarget = target.getAnchor(ConstraintAnchor.Type.LEFT);
            left3.connect(leftTarget, 0, creator);
            ConstraintAnchor right3 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor rightTarget = target.getAnchor(ConstraintAnchor.Type.RIGHT);
            right3.connect(rightTarget, 0, creator);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(constraintTo), 0, creator);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && constraintTo == ConstraintAnchor.Type.CENTER_Y) {
            ConstraintAnchor top2 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor topTarget = target.getAnchor(ConstraintAnchor.Type.TOP);
            top2.connect(topTarget, 0, creator);
            ConstraintAnchor bottom2 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor bottomTarget = target.getAnchor(ConstraintAnchor.Type.BOTTOM);
            bottom2.connect(bottomTarget, 0, creator);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(constraintTo), 0, creator);
        } else {
            ConstraintAnchor fromAnchor = getAnchor(constraintFrom);
            ConstraintAnchor toAnchor = target.getAnchor(constraintTo);
            if (fromAnchor.isValidConnection(toAnchor)) {
                if (constraintFrom == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor top3 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor bottom3 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (top3 != null) {
                        top3.reset();
                    }
                    if (bottom3 != null) {
                        bottom3.reset();
                    }
                    margin2 = 0;
                } else {
                    if (constraintFrom == ConstraintAnchor.Type.TOP || constraintFrom == ConstraintAnchor.Type.BOTTOM) {
                        ConstraintAnchor baseline = getAnchor(ConstraintAnchor.Type.BASELINE);
                        if (baseline != null) {
                            baseline.reset();
                        }
                        ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
                        if (center.getTarget() != toAnchor) {
                            center.reset();
                        }
                        ConstraintAnchor opposite = getAnchor(constraintFrom).getOpposite();
                        ConstraintAnchor centerY2 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                        if (centerY2.isConnected()) {
                            opposite.reset();
                            centerY2.reset();
                        }
                    } else if (constraintFrom == ConstraintAnchor.Type.LEFT || constraintFrom == ConstraintAnchor.Type.RIGHT) {
                        ConstraintAnchor center2 = getAnchor(ConstraintAnchor.Type.CENTER);
                        if (center2.getTarget() != toAnchor) {
                            center2.reset();
                        }
                        ConstraintAnchor opposite2 = getAnchor(constraintFrom).getOpposite();
                        ConstraintAnchor centerX2 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                        if (centerX2.isConnected()) {
                            opposite2.reset();
                            centerX2.reset();
                        }
                    }
                    margin2 = margin;
                }
                fromAnchor.connect(toAnchor, margin2, strength, creator);
                toAnchor.getOwner().connectedTo(fromAnchor.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (this instanceof ConstraintWidgetContainer) {
            return;
        }
        if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (getWidth() == getWrapWidth()) {
                setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getWidth() > getMinWidth()) {
                setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
        if (getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (getHeight() == getWrapHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getHeight() > getMinHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
    }

    public void resetAnchor(ConstraintAnchor anchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer)) {
            ConstraintWidgetContainer parent = (ConstraintWidgetContainer) getParent();
            if (parent.handlesInternalConstraints()) {
                return;
            }
        }
        ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor centerX = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor centerY = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (anchor == center) {
            if (left.isConnected() && right.isConnected() && left.getTarget() == right.getTarget()) {
                left.reset();
                right.reset();
            }
            if (top.isConnected() && bottom.isConnected() && top.getTarget() == bottom.getTarget()) {
                top.reset();
                bottom.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
            this.mVerticalBiasPercent = 0.5f;
        } else if (anchor == centerX) {
            if (left.isConnected() && right.isConnected() && left.getTarget().getOwner() == right.getTarget().getOwner()) {
                left.reset();
                right.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
        } else if (anchor == centerY) {
            if (top.isConnected() && bottom.isConnected() && top.getTarget().getOwner() == bottom.getTarget().getOwner()) {
                top.reset();
                bottom.reset();
            }
            this.mVerticalBiasPercent = 0.5f;
        } else if (anchor == left || anchor == right) {
            if (left.isConnected() && left.getTarget() == right.getTarget()) {
                center.reset();
            }
        } else if ((anchor == top || anchor == bottom) && top.isConnected() && top.getTarget() == bottom.getTarget()) {
            center.reset();
        }
        anchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer)) {
            ConstraintWidgetContainer parentContainer = (ConstraintWidgetContainer) getParent();
            if (parentContainer.handlesInternalConstraints()) {
                return;
            }
        }
        int mAnchorsSize = this.mAnchors.size();
        for (int i = 0; i < mAnchorsSize; i++) {
            ConstraintAnchor anchor = this.mAnchors.get(i);
            anchor.reset();
        }
    }

    public void resetAnchors(int connectionCreator) {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer)) {
            ConstraintWidgetContainer parentContainer = (ConstraintWidgetContainer) getParent();
            if (parentContainer.handlesInternalConstraints()) {
                return;
            }
        }
        int mAnchorsSize = this.mAnchors.size();
        for (int i = 0; i < mAnchorsSize; i++) {
            ConstraintAnchor anchor = this.mAnchors.get(i);
            if (connectionCreator == anchor.getConnectionCreator()) {
                if (anchor.isVerticalAnchor()) {
                    setVerticalBiasPercent(DEFAULT_BIAS);
                } else {
                    setHorizontalBiasPercent(DEFAULT_BIAS);
                }
                anchor.reset();
            }
        }
    }

    public void disconnectWidget(ConstraintWidget widget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int anchorsSize = anchors.size();
        for (int i = 0; i < anchorsSize; i++) {
            ConstraintAnchor anchor = anchors.get(i);
            if (anchor.isConnected() && anchor.getTarget().getOwner() == widget) {
                anchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget widget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int anchorsSize = anchors.size();
        for (int i = 0; i < anchorsSize; i++) {
            ConstraintAnchor anchor = anchors.get(i);
            if (anchor.isConnected() && anchor.getTarget().getOwner() == widget && anchor.getConnectionCreator() == 2) {
                anchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type anchorType) {
        switch (anchorType) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(anchorType.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int orientation) {
        if (orientation == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (orientation == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[0] = behaviour;
        if (behaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[1] = behaviour;
        if (behaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            if (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight) {
                return true;
            }
            return false;
        }
        return true;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintWidget found = null;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget tmp = this;
        while (found == null && tmp != null) {
            ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
            ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
            if (target == getParent()) {
                ConstraintWidget found2 = tmp;
                return found2;
            }
            ConstraintAnchor targetAnchor = target != null ? target.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget() : null;
            if (targetAnchor != null && targetAnchor.getOwner() != tmp) {
                found = tmp;
            } else {
                tmp = target;
            }
        }
        return found;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            if (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom) {
                return true;
            }
            return false;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintWidget found = null;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget tmp = this;
        while (found == null && tmp != null) {
            ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
            ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
            if (target == getParent()) {
                ConstraintWidget found2 = tmp;
                return found2;
            }
            ConstraintAnchor targetAnchor = target != null ? target.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget() : null;
            if (targetAnchor != null && targetAnchor.getOwner() != tmp) {
                found = tmp;
            } else {
                tmp = target;
            }
        }
        return found;
    }

    private boolean isChainHead(int orientation) {
        int offset = orientation * 2;
        if (this.mListAnchors[offset].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[offset].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[offset] && constraintAnchorArr[offset + 1].mTarget != null && this.mListAnchors[offset + 1].mTarget.mTarget == this.mListAnchors[offset + 1]) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01ca, code lost:
        if (r0 != (-1)) goto L75;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0279 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:172:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addToSolver(androidx.constraintlayout.solver.LinearSystem r53) {
        /*
            Method dump skipped, instructions count: 903
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.solver.LinearSystem):void");
    }

    public void setupDimensionRatio(boolean hparentWrapContent, boolean vparentWrapContent, boolean horizontalDimensionFixed, boolean verticalDimensionFixed) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (horizontalDimensionFixed && !verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!horizontalDimensionFixed && verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (hparentWrapContent && !vparentWrapContent) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!hparentWrapContent && vparentWrapContent) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1 && hparentWrapContent && vparentWrapContent) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    private void applyConstraints(LinearSystem system, boolean parentWrapContent, SolverVariable parentMin, SolverVariable parentMax, DimensionBehaviour dimensionBehaviour, boolean wrapContent, ConstraintAnchor beginAnchor, ConstraintAnchor endAnchor, int beginPosition, int dimension, int minDimension, int maxDimension, float bias, boolean useRatio, boolean inChain, int matchConstraintDefault, int matchMinDimension, int matchMaxDimension, float matchPercentDimension, boolean applyPosition) {
        int matchConstraintDefault2;
        boolean variableSize;
        int dimension2;
        boolean variableSize2;
        int i;
        int dimension3;
        int matchConstraintDefault3;
        int matchMinDimension2;
        int matchMaxDimension2;
        SolverVariable beginTarget;
        int numConnections;
        SolverVariable endTarget;
        SolverVariable percentEnd;
        SolverVariable percentEnd2;
        int matchMaxDimension3;
        int matchMaxDimension4;
        int numConnections2;
        int i2;
        SolverVariable begin;
        int i3;
        int matchConstraintDefault4;
        int matchConstraintDefault5;
        SolverVariable beginTarget2;
        int applyBoundsCheck;
        boolean applyBoundsCheck2;
        int centeringStrength;
        SolverVariable end;
        SolverVariable begin2;
        int startStrength;
        int endStrength;
        int applyBoundsCheck3;
        int strength;
        int strength2;
        int i4;
        SolverVariable begin3 = system.createObjectVariable(beginAnchor);
        SolverVariable end2 = system.createObjectVariable(endAnchor);
        SolverVariable beginTarget3 = system.createObjectVariable(beginAnchor.getTarget());
        SolverVariable endTarget2 = system.createObjectVariable(endAnchor.getTarget());
        if (system.graphOptimizer && beginAnchor.getResolutionNode().state == 1 && endAnchor.getResolutionNode().state == 1) {
            if (LinearSystem.getMetrics() != null) {
                LinearSystem.getMetrics().resolvedWidgets++;
            }
            beginAnchor.getResolutionNode().addResolvedValue(system);
            endAnchor.getResolutionNode().addResolvedValue(system);
            if (!inChain && parentWrapContent) {
                system.addGreaterThan(parentMax, end2, 0, 6);
                return;
            }
            return;
        }
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean isBeginConnected = beginAnchor.isConnected();
        boolean isEndConnected = endAnchor.isConnected();
        boolean isCenterConnected = this.mCenter.isConnected();
        int numConnections3 = isBeginConnected ? 0 + 1 : 0;
        if (isEndConnected) {
            numConnections3++;
        }
        if (isCenterConnected) {
            numConnections3++;
        }
        int numConnections4 = numConnections3;
        if (!useRatio) {
            matchConstraintDefault2 = matchConstraintDefault;
        } else {
            matchConstraintDefault2 = 3;
        }
        int i5 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[dimensionBehaviour.ordinal()];
        if (i5 == 1) {
            variableSize = false;
        } else if (i5 == 2) {
            variableSize = false;
        } else if (i5 == 3) {
            variableSize = false;
        } else if (i5 != 4) {
            variableSize = false;
        } else if (matchConstraintDefault2 != 4) {
            variableSize = true;
        } else {
            variableSize = false;
        }
        if (this.mVisibility != 8) {
            dimension2 = dimension;
            variableSize2 = variableSize;
        } else {
            dimension2 = 0;
            variableSize2 = false;
        }
        if (applyPosition) {
            if (!isBeginConnected && !isEndConnected && !isCenterConnected) {
                system.addEquality(begin3, beginPosition);
            } else if (isBeginConnected && !isEndConnected) {
                system.addEquality(begin3, beginTarget3, beginAnchor.getMargin(), 6);
            }
        }
        if (!variableSize2) {
            if (wrapContent) {
                system.addEquality(end2, begin3, 0, 3);
                if (minDimension <= 0) {
                    i4 = 6;
                } else {
                    i4 = 6;
                    system.addGreaterThan(end2, begin3, minDimension, 6);
                }
                if (maxDimension < Integer.MAX_VALUE) {
                    system.addLowerThan(end2, begin3, maxDimension, i4);
                }
            } else {
                system.addEquality(end2, begin3, dimension2, 6);
            }
            matchMinDimension2 = matchMinDimension;
            matchMaxDimension3 = matchMaxDimension;
            matchMaxDimension4 = dimension2;
            matchConstraintDefault3 = matchConstraintDefault2;
            endTarget = endTarget2;
            beginTarget = beginTarget3;
            numConnections = numConnections4;
        } else {
            int matchMinDimension3 = matchMinDimension;
            if (matchMinDimension3 == -2) {
                matchMinDimension3 = dimension2;
            }
            int matchMaxDimension5 = matchMaxDimension;
            if (matchMaxDimension5 == -2) {
                matchMaxDimension5 = dimension2;
            }
            if (matchMinDimension3 <= 0) {
                i = 6;
            } else {
                i = 6;
                system.addGreaterThan(end2, begin3, matchMinDimension3, 6);
                dimension2 = Math.max(dimension2, matchMinDimension3);
            }
            if (matchMaxDimension5 > 0) {
                system.addLowerThan(end2, begin3, matchMaxDimension5, i);
                dimension2 = Math.min(dimension2, matchMaxDimension5);
            }
            if (matchConstraintDefault2 == 1) {
                if (parentWrapContent) {
                    system.addEquality(end2, begin3, dimension2, 6);
                    dimension3 = dimension2;
                    matchConstraintDefault3 = matchConstraintDefault2;
                    matchMinDimension2 = matchMinDimension3;
                    matchMaxDimension2 = matchMaxDimension5;
                    beginTarget = beginTarget3;
                    numConnections = numConnections4;
                    endTarget = endTarget2;
                } else if (inChain) {
                    system.addEquality(end2, begin3, dimension2, 4);
                    dimension3 = dimension2;
                    matchConstraintDefault3 = matchConstraintDefault2;
                    matchMinDimension2 = matchMinDimension3;
                    matchMaxDimension2 = matchMaxDimension5;
                    beginTarget = beginTarget3;
                    numConnections = numConnections4;
                    endTarget = endTarget2;
                } else {
                    system.addEquality(end2, begin3, dimension2, 1);
                    dimension3 = dimension2;
                    matchConstraintDefault3 = matchConstraintDefault2;
                    matchMinDimension2 = matchMinDimension3;
                    matchMaxDimension2 = matchMaxDimension5;
                    beginTarget = beginTarget3;
                    numConnections = numConnections4;
                    endTarget = endTarget2;
                }
            } else if (matchConstraintDefault2 != 2) {
                dimension3 = dimension2;
                matchConstraintDefault3 = matchConstraintDefault2;
                matchMinDimension2 = matchMinDimension3;
                matchMaxDimension2 = matchMaxDimension5;
                beginTarget = beginTarget3;
                numConnections = numConnections4;
                endTarget = endTarget2;
            } else {
                int dimension4 = dimension2;
                if (beginAnchor.getType() == ConstraintAnchor.Type.TOP || beginAnchor.getType() == ConstraintAnchor.Type.BOTTOM) {
                    SolverVariable percentBegin = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.TOP));
                    percentEnd = percentBegin;
                    percentEnd2 = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
                } else {
                    SolverVariable percentBegin2 = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                    percentEnd = percentBegin2;
                    percentEnd2 = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
                }
                matchConstraintDefault3 = matchConstraintDefault2;
                dimension3 = dimension4;
                numConnections = numConnections4;
                matchMinDimension2 = matchMinDimension3;
                matchMaxDimension2 = matchMaxDimension5;
                endTarget = endTarget2;
                beginTarget = beginTarget3;
                system.addConstraint(system.createRow().createRowDimensionRatio(end2, begin3, percentEnd2, percentEnd, matchPercentDimension));
                variableSize2 = false;
            }
            if (variableSize2 && numConnections != 2 && !useRatio) {
                variableSize2 = false;
                int dimension5 = dimension3;
                int d = Math.max(matchMinDimension2, dimension5);
                if (matchMaxDimension2 > 0) {
                    d = Math.min(matchMaxDimension2, d);
                }
                system.addEquality(end2, begin3, d, 6);
                matchMaxDimension3 = matchMaxDimension2;
                matchMaxDimension4 = dimension5;
            } else {
                matchMaxDimension3 = matchMaxDimension2;
                matchMaxDimension4 = dimension3;
            }
        }
        if (!applyPosition) {
            numConnections2 = numConnections;
            i2 = 6;
            begin = begin3;
        } else if (!inChain) {
            if (isBeginConnected || isEndConnected || isCenterConnected) {
                i3 = 0;
                if (isBeginConnected && !isEndConnected) {
                    if (parentWrapContent) {
                        system.addGreaterThan(parentMax, end2, 0, 5);
                        matchConstraintDefault4 = 6;
                    } else {
                        matchConstraintDefault4 = 6;
                    }
                } else if (!isBeginConnected && isEndConnected) {
                    system.addEquality(end2, endTarget, -endAnchor.getMargin(), 6);
                    if (parentWrapContent) {
                        system.addGreaterThan(begin3, parentMin, 0, 5);
                        matchConstraintDefault4 = 6;
                    } else {
                        matchConstraintDefault4 = 6;
                    }
                } else if (isBeginConnected && isEndConnected) {
                    boolean applyCentering = false;
                    if (!variableSize2) {
                        matchConstraintDefault5 = matchConstraintDefault3;
                        beginTarget2 = beginTarget;
                        applyBoundsCheck = 0;
                        applyBoundsCheck2 = true;
                        centeringStrength = 5;
                    } else {
                        if (parentWrapContent && minDimension == 0) {
                            system.addGreaterThan(end2, begin3, 0, 6);
                        }
                        matchConstraintDefault5 = matchConstraintDefault3;
                        if (matchConstraintDefault5 == 0) {
                            if (matchMaxDimension3 <= 0 && matchMinDimension2 <= 0) {
                                strength = 0;
                                strength2 = 6;
                            } else {
                                strength = 1;
                                strength2 = 4;
                            }
                            beginTarget2 = beginTarget;
                            system.addEquality(begin3, beginTarget2, beginAnchor.getMargin(), strength2);
                            system.addEquality(end2, endTarget, -endAnchor.getMargin(), strength2);
                            applyCentering = (matchMaxDimension3 > 0 || matchMinDimension2 > 0) ? true : true;
                            centeringStrength = 5;
                            applyBoundsCheck = strength;
                            applyBoundsCheck2 = applyCentering;
                        } else {
                            beginTarget2 = beginTarget;
                            if (matchConstraintDefault5 == 1) {
                                applyBoundsCheck = 1;
                                applyBoundsCheck2 = true;
                                centeringStrength = 6;
                            } else if (matchConstraintDefault5 != 3) {
                                applyBoundsCheck = 0;
                                applyBoundsCheck2 = false;
                                centeringStrength = 5;
                            } else {
                                int strength3 = 4;
                                if (!useRatio) {
                                    applyBoundsCheck3 = 1;
                                    if (this.mResolvedDimensionRatioSide != -1 && matchMaxDimension3 <= 0) {
                                        strength3 = 6;
                                    }
                                } else {
                                    applyBoundsCheck3 = 1;
                                }
                                system.addEquality(begin3, beginTarget2, beginAnchor.getMargin(), strength3);
                                system.addEquality(end2, endTarget, -endAnchor.getMargin(), strength3);
                                applyBoundsCheck = applyBoundsCheck3;
                                applyBoundsCheck2 = true;
                                centeringStrength = 5;
                            }
                        }
                    }
                    int startStrength2 = 5;
                    int endStrength2 = 5;
                    boolean applyStartConstraint = parentWrapContent;
                    boolean applyEndConstraint = parentWrapContent;
                    if (applyBoundsCheck2) {
                        end = end2;
                        begin2 = begin3;
                        system.addCentering(begin3, beginTarget2, beginAnchor.getMargin(), bias, endTarget, end2, endAnchor.getMargin(), centeringStrength);
                        boolean isBeginAnchorBarrier = beginAnchor.mTarget.mOwner instanceof Barrier;
                        matchConstraintDefault4 = 6;
                        boolean isEndAnchorBarrier = endAnchor.mTarget.mOwner instanceof Barrier;
                        if (isBeginAnchorBarrier && !isEndAnchorBarrier) {
                            endStrength2 = 6;
                            applyEndConstraint = true;
                        } else if (!isBeginAnchorBarrier && isEndAnchorBarrier) {
                            startStrength2 = 6;
                            applyStartConstraint = true;
                        }
                    } else {
                        end = end2;
                        begin2 = begin3;
                        matchConstraintDefault4 = 6;
                    }
                    if (applyBoundsCheck == 0) {
                        startStrength = startStrength2;
                        endStrength = endStrength2;
                    } else {
                        startStrength = 6;
                        endStrength = 6;
                    }
                    if ((!variableSize2 && applyStartConstraint) || applyBoundsCheck != 0) {
                        system.addGreaterThan(begin2, beginTarget2, beginAnchor.getMargin(), startStrength);
                    }
                    if ((variableSize2 || !applyEndConstraint) && applyBoundsCheck == 0) {
                        end2 = end;
                    } else {
                        end2 = end;
                        system.addLowerThan(end2, endTarget, -endAnchor.getMargin(), endStrength);
                    }
                    if (!parentWrapContent) {
                        i3 = 0;
                    } else {
                        i3 = 0;
                        system.addGreaterThan(begin2, parentMin, 0, matchConstraintDefault4);
                    }
                } else {
                    matchConstraintDefault4 = 6;
                }
            } else if (!parentWrapContent) {
                i3 = 0;
                matchConstraintDefault4 = 6;
            } else {
                i3 = 0;
                system.addGreaterThan(parentMax, end2, 0, 5);
                matchConstraintDefault4 = 6;
            }
            if (parentWrapContent) {
                system.addGreaterThan(parentMax, end2, i3, matchConstraintDefault4);
                return;
            }
            return;
        } else {
            numConnections2 = numConnections;
            i2 = 6;
            begin = begin3;
        }
        if (numConnections2 < 2 && parentWrapContent) {
            system.addGreaterThan(begin, parentMin, 0, i2);
            system.addGreaterThan(parentMax, end2, 0, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour = new int[DimensionBehaviour.values().length];

        static {
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type = new int[ConstraintAnchor.Type.values().length];
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    public void updateFromSolver(LinearSystem system) {
        int left = system.getObjectVariableValue(this.mLeft);
        int top = system.getObjectVariableValue(this.mTop);
        int right = system.getObjectVariableValue(this.mRight);
        int bottom = system.getObjectVariableValue(this.mBottom);
        int w = right - left;
        int h = bottom - top;
        if (w < 0 || h < 0 || left == Integer.MIN_VALUE || left == Integer.MAX_VALUE || top == Integer.MIN_VALUE || top == Integer.MAX_VALUE || right == Integer.MIN_VALUE || right == Integer.MAX_VALUE || bottom == Integer.MIN_VALUE || bottom == Integer.MAX_VALUE) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }
        setFrame(left, top, right, bottom);
    }
}