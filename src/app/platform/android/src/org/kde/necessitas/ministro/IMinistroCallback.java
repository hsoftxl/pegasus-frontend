/*
    Hand-written equivalent of IMinistroCallback.aidl for Qt 5.15 android bindings.
    AGP 8 no longer compiles aidl files from external Qt source dirs,
    so we provide the AIDL-generated classes directly.
*/

package org.kde.necessitas.ministro;

public interface IMinistroCallback extends android.os.IInterface {

    public void loaderReady(android.os.Bundle loaderParams) throws android.os.RemoteException;

    public static abstract class Stub extends android.os.Binder implements org.kde.necessitas.ministro.IMinistroCallback {
        private static final java.lang.String DESCRIPTOR = "org.kde.necessitas.ministro.IMinistroCallback";

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static org.kde.necessitas.ministro.IMinistroCallback asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof org.kde.necessitas.ministro.IMinistroCallback))) {
                return ((org.kde.necessitas.ministro.IMinistroCallback) iin);
            }
            return new org.kde.necessitas.ministro.IMinistroCallback.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            java.lang.String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_loaderReady: {
                    data.enforceInterface(descriptor);
                    android.os.Bundle _arg0;
                    _arg0 = data.readBundle();
                    this.loaderReady(_arg0);
                    return true;
                }
                default:
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements org.kde.necessitas.ministro.IMinistroCallback {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public void loaderReady(android.os.Bundle loaderParams) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((loaderParams != null)) {
                        _data.writeInt(1);
                        loaderParams.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_loaderReady, _data, null, android.os.IBinder.FLAG_ONEWAY);
                } finally {
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_loaderReady = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    }
}
