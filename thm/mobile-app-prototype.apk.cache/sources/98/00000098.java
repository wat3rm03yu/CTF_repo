package androidx.appcompat.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ColorStateListInflaterCompat;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public final class AppCompatResources {
    private static final String LOG_TAG = "AppCompatResources";
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();
    private static final WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap<>(0);
    private static final Object sColorStateCacheLock = new Object();

    private AppCompatResources() {
    }

    public static ColorStateList getColorStateList(Context context, int resId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(resId);
        }
        ColorStateList csl = getCachedColorStateList(context, resId);
        if (csl != null) {
            return csl;
        }
        ColorStateList csl2 = inflateColorStateList(context, resId);
        if (csl2 != null) {
            addColorStateListToCache(context, resId, csl2);
            return csl2;
        }
        return ContextCompat.getColorStateList(context, resId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return AppCompatDrawableManager.get().getDrawable(context, resId);
    }

    private static ColorStateList inflateColorStateList(Context context, int resId) {
        if (isColorInt(context, resId)) {
            return null;
        }
        Resources r = context.getResources();
        XmlPullParser xml = r.getXml(resId);
        try {
            return ColorStateListInflaterCompat.createFromXml(r, xml, context.getTheme());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    private static ColorStateList getCachedColorStateList(Context context, int resId) {
        ColorStateListCacheEntry entry;
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> entries = sColorStateCaches.get(context);
            if (entries != null && entries.size() > 0 && (entry = entries.get(resId)) != null) {
                if (entry.configuration.equals(context.getResources().getConfiguration())) {
                    return entry.value;
                }
                entries.remove(resId);
            }
            return null;
        }
    }

    private static void addColorStateListToCache(Context context, int resId, ColorStateList value) {
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> entries = sColorStateCaches.get(context);
            if (entries == null) {
                entries = new SparseArray<>();
                sColorStateCaches.put(context, entries);
            }
            entries.append(resId, new ColorStateListCacheEntry(value, context.getResources().getConfiguration()));
        }
    }

    private static boolean isColorInt(Context context, int resId) {
        Resources r = context.getResources();
        TypedValue value = getTypedValue();
        r.getValue(resId, value, true);
        return value.type >= 28 && value.type <= 31;
    }

    private static TypedValue getTypedValue() {
        TypedValue tv = TL_TYPED_VALUE.get();
        if (tv == null) {
            TypedValue tv2 = new TypedValue();
            TL_TYPED_VALUE.set(tv2);
            return tv2;
        }
        return tv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ColorStateListCacheEntry {
        final Configuration configuration;
        final ColorStateList value;

        ColorStateListCacheEntry(ColorStateList value, Configuration configuration) {
            this.value = value;
            this.configuration = configuration;
        }
    }
}