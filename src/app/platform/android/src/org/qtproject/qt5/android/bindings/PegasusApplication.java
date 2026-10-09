// Pegasus Frontend
// Workaround for Qt 5.15.2 startup race on Android 15+:
// QtActivityDelegate registers a DisplayManager listener before the Qt
// platform plugin is loaded, so onDisplayChanged can fire before
// QtNative.handleOrientationChanged is registered, crashing with
// UnsatisfiedLinkError.  Pre-loading the main library in
// attachBaseContext() (the earliest application callback) makes the
// JNI export Java_org_qtproject_qt5_android_QtNative_handleOrientationChanged
// resolvable before any display callback can arrive, and registers the
// library with the app class loader.

package org.qtproject.qt5.android.bindings;

import android.content.Context;

public class PegasusApplication extends QtApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        preloadMainLibrary();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preloadMainLibrary();
    }

    private static void preloadMainLibrary() {
        try {
            // Load the app's main library early; System.loadLibrary binds it
            // to the class loader so ART can find our exported JNI symbol.
            System.loadLibrary("pegasus-fe");
        }
        catch (UnsatisfiedLinkError e) {
            // Qt's own loader will retry later; don't abort startup here.
            android.util.Log.w(QtTAG, "pegasus-fe preload failed: " + e.getMessage());
        }
    }
}
