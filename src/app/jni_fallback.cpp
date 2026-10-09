/*
 * JNI fallback for Qt 5.15.2 Android.
 *
 * Qt 5.15.2 has a startup race: QtActivityDelegate registers a
 * DisplayManager.DisplayListener BEFORE the Qt platform plugin
 * (qtforandroid) is loaded, so on Android 15+ devices the initial
 * onDisplayChanged callback can fire before JNI_OnLoad has had a
 * chance to RegisterNatives for QtNative.handleOrientationChanged,
 * causing an UnsatisfiedLinkError crash.
 *
 * This TU exports the JNI symbol directly from the application's own
 * main library (libpegasus-fe), which is loaded before the platform
 * plugin, so ART can resolve the method via the export table even
 * before the plugin registers it.  The app is locked to landscape
 * (screenOrientation=userLandscape), so the notification is a no-op.
 */

#include <jni.h>
#include <android/log.h>

#define APP_LOG_TAG "PegasusFE"

extern "C" JNIEXPORT void JNICALL
Java_org_qtproject_qt5_android_QtNative_handleOrientationChanged(JNIEnv *, jobject, jint, jint)
{
    // Application is locked to landscape; orientation changes are not
    // expected.  Nothing to do here except acknowledge the callback.
    __android_log_print(ANDROID_LOG_DEBUG, APP_LOG_TAG,
                        "handleOrientationChanged fallback invoked (no-op)");
}
