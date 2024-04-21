package androidx.core.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/* loaded from: classes.dex */
public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    private AccessibilityServiceInfoCompat() {
    }

    public static String loadDescription(AccessibilityServiceInfo info, PackageManager packageManager) {
        if (Build.VERSION.SDK_INT >= 16) {
            return info.loadDescription(packageManager);
        }
        return info.getDescription();
    }

    public static String feedbackTypeToString(int feedbackType) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (feedbackType > 0) {
            int feedbackTypeFlag = 1 << Integer.numberOfTrailingZeros(feedbackType);
            feedbackType &= feedbackTypeFlag ^ (-1);
            if (builder.length() > 1) {
                builder.append(", ");
            }
            if (feedbackTypeFlag == 1) {
                builder.append("FEEDBACK_SPOKEN");
            } else if (feedbackTypeFlag == 2) {
                builder.append("FEEDBACK_HAPTIC");
            } else if (feedbackTypeFlag == 4) {
                builder.append("FEEDBACK_AUDIBLE");
            } else if (feedbackTypeFlag == 8) {
                builder.append("FEEDBACK_VISUAL");
            } else if (feedbackTypeFlag == 16) {
                builder.append("FEEDBACK_GENERIC");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static String flagToString(int flag) {
        if (flag != 1) {
            if (flag != 2) {
                if (flag != 4) {
                    if (flag != 8) {
                        if (flag != 16) {
                            if (flag == 32) {
                                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
                            }
                            return null;
                        }
                        return "FLAG_REPORT_VIEW_IDS";
                    }
                    return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
                }
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            }
            return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
        }
        return "DEFAULT";
    }

    public static int getCapabilities(AccessibilityServiceInfo info) {
        if (Build.VERSION.SDK_INT >= 18) {
            return info.getCapabilities();
        }
        if (info.getCanRetrieveWindowContent()) {
            return 1;
        }
        return 0;
    }

    public static String capabilityToString(int capability) {
        if (capability != 1) {
            if (capability != 2) {
                if (capability != 4) {
                    if (capability == 8) {
                        return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
                    }
                    return "UNKNOWN";
                }
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
        }
        return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
    }
}