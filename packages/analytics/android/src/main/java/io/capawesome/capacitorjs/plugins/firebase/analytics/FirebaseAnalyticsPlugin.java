package io.capawesome.capacitorjs.plugins.firebase.analytics;

import android.os.Bundle;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "FirebaseAnalytics")
public class FirebaseAnalyticsPlugin extends Plugin {

    public static final String ERROR_KEY_MISSING = "key must be provided.";
    public static final String ERROR_ENABLED_MISSING = "enabled must be provided.";
    public static final String ERROR_NAME_MISSING = "name must be provided.";
    private FirebaseAnalytics implementation;

    public void load() {
        implementation = new FirebaseAnalytics(this.getContext(), this.getBridge());
    }

    @PluginMethod
    public void setUserId(PluginCall call) {
        try {
            String userId = call.getString("userId", null);
            implementation.setUserId(userId);
            call.resolve();
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void setUserProperty(PluginCall call) {
        try {
            String key = call.getString("key");
            if (key == null) {
                call.reject(ERROR_KEY_MISSING);
                return;
            }
            String value = call.getString("value", null);
            implementation.setUserProperty(key, value);
            call.resolve();
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void setCurrentScreen(PluginCall call) {
        try {
            String screenName = call.getString("screenName", null);
            String screenClassOverride = call.getString("screenClassOverride", null);
            implementation.setCurrentScreen(screenName, screenClassOverride);
            call.resolve();
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void logEvent(PluginCall call) {
        try {
            String name = call.getString("name");
            if (name == null) {
                call.reject(ERROR_NAME_MISSING);
                return;
            }
            JSObject params = call.getObject("params");
            implementation.logEvent(name, params);
            call.resolve();
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void setSessionTimeoutDuration(PluginCall call) {
        try {
            Long duration = call.getLong("duration", 1800000L);
            implementation.setSessionTimeoutDuration(duration);
            call.resolve();
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void setEnabled(PluginCall call) {
        try {
            Boolean enabled = call.getBoolean("enabled");
            if (enabled == null) {
                call.reject(ERROR_ENABLED_MISSING);
                return;
            }
            implementation.setEnabled(enabled);
            call.resolve();
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }

    @PluginMethod
    public void isEnabled(PluginCall call) {
        call.unimplemented("Not implemented on Android.");
    }

    @PluginMethod
    public void resetAnalyticsData(PluginCall call) {
        try {
            implementation.resetAnalyticsData();
            call.resolve();
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }
}
