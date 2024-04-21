package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, int orientation) {
        int offset;
        int chainsSize;
        ChainHead[] chainsArray;
        if (orientation == 0) {
            offset = 0;
            chainsSize = constraintWidgetContainer.mHorizontalChainsSize;
            chainsArray = constraintWidgetContainer.mHorizontalChainsArray;
        } else {
            offset = 2;
            chainsSize = constraintWidgetContainer.mVerticalChainsSize;
            chainsArray = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i = 0; i < chainsSize; i++) {
            ChainHead first = chainsArray[i];
            first.define();
            if (constraintWidgetContainer.optimizeFor(4)) {
                if (!Optimizer.applyChainOptimized(constraintWidgetContainer, system, orientation, offset, first)) {
                    applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
                }
            } else {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:296:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0658  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x065d  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0678  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0684  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r44, androidx.constraintlayout.solver.LinearSystem r45, int r46, int r47, androidx.constraintlayout.solver.widgets.ChainHead r48) {
        /*
            Method dump skipped, instructions count: 1722
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }
}