/*
    Hand-written equivalent of IMinistro.aidl for Qt 5.15 android bindings.
    AGP 8 no longer compiles aidl files from external Qt source dirs,
    so we provide the AIDL-generated classes directly.
*/

package org.kde.necessitas.ministro;

public interface IMinistro extends android.os.IInterface {

    public void requestLoader(org.kde.necessitas.ministro.IMinistroCallback callback, android.os.Bundle parameters) throws android.os.RemoteException;

    public static abstract class Stub extends android.os.Binder implements org.kde.necessitas.ministro.IMinistro {
        private static final java.lang.String DESCRIPTOR = "org.kde.necessitas.ministro.IMinistro";

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static org.kde.necessitas.ministro.IMinistro asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof org.kde.necessitas.ministro.IMinistro))) {
                return ((org.kde.necessitas.ministro.IMinistro) iin);
            }
            return new org.kde.necessitas.ministro.IMinistro.Stub.Proxy(obj);
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
                case TRANSACTION_requestLoader: {
                    data.enforceInterface(descriptor);
                    org.kde.necessitas.ministro.IMinistroCallback _arg0;
                    _arg0 = org.kde.necessitas.ministro.IMinistroCallback.Stub.asInterface(data.readStrongBinder());
                    android.os.Bundle _arg1;
                    _arg1 = data.readBundle();
                    this.requestLoader(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                default:
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements org.kde.necessitas.ministro.IMinistro {
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
            public void requestLoader(org.kde.necessitas.ministro.IMinistroCallback callback, android.os.Bundle parameters) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback != null)) ? (callback.asBinder()) : (null)));
                    if ((parameters != null)) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_requestLoader, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_requestLoader = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    }
}
