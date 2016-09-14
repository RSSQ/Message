package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button show_bt;
    private Button abolish_bt;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化事件
        initVIew();

    }

    /**
     * 初始化控件
     */
    private void initVIew(){

        show_bt =(Button) findViewById(R.id.show_bt);
        abolish_bt=(Button) findViewById(R.id.abolish_bt);

        show_bt.setOnClickListener(this);
        abolish_bt.setOnClickListener(this);
    }


    public void onClick(View view){
        switch(view.getId()){
            case R.id.show_bt:
                //进程间的，主要是吧一个动作交给另外一个应用程序，用PendingIntent包裹一下
                //bendingIntent可以打开一个四大组件，用其静态方法，get。。4
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel://18133626149"));

                //1 上下文，2 请求码，3 用不到随机设置 4 指定点击后的状态标识
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //直接new出来的对象在安卓3.0以后就过时，
                //Notification notification=new Notification();
                //使用链式调用的方法，创建Notification对象的同时往里面进行设置，是主流的方法。
                Notification notification=new Notification.Builder(this)//参数是上下文
                        //设置通知栏的标题
                        .setContentTitle("Come out,let's do it!")
                        //设置通知栏的内容
                        .setContentText("Study hard")
                        //设置通知栏里的小图片;
                        .setSmallIcon(R.drawable.a)
                        //设置通知栏里的大图片（通知栏拉下来的图片）
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.a))
                        //设置通知栏每次到时，就有声音。
                        //.setSound(Uri.parse())
                        //设置通知栏每次到来时，就会有震动
                        //.setVibrate(new long[]{100,500,600})
                        //使用系统默认的声音和震动，等。。。
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentIntent(pendingIntent)
                        //设置当通知栏里的提示被点击执行时，通知栏会消失在桌面
                        //如果不设置此方法的，就会出现通知栏提示被点击的
                        .setAutoCancel(true)



                        .build();
                //让通知栏显示，1.给Notification一个ID方便，
                notificationManager.notify(1,notification);

                break;
            case R.id.abolish_bt:
                //就是用NotificationManager,来取消指定int标识的通知栏
                notificationManager.cancel(1);
                break;
            default:
                break;
        }
    }


}
