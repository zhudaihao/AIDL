package cn.zdh.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btn_ok,  btn_callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_callback = (Button) findViewById(R.id.btn_callback);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setComponent(new ComponentName("cn.zdh.aidl", "cn.zdh.aidl.AIDLService"));

                    bindService(intent, conn, BIND_AUTO_CREATE);

            }
        });




        btn_callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    //调用进程方法
                    try {
                        add.show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

            }
        });


    }

    private Add add;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //获取AIDL实例
            add = Add.Stub.asInterface(iBinder);


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //回收资源
            add = null;
        }
    };


    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();


    }
}
