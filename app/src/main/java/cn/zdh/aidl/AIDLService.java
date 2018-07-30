package cn.zdh.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class AIDLService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }


    //实现AIDL接口方法
    private IBinder iBinder = new Add.Stub() {

        @Override
        public void show() throws RemoteException {

            Log.e("agr", "----------测试---------");


            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "后台服务", Toast.LENGTH_LONG).show();
                }
            });

        }
    };


}
