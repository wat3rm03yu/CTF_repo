package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final GeneratedAdapter mGeneratedAdapter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        this.mGeneratedAdapter = generatedAdapter;
    }

    @Override // androidx.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        this.mGeneratedAdapter.callMethods(source, event, false, null);
        this.mGeneratedAdapter.callMethods(source, event, true, null);
    }
}