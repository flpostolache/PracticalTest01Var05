package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread{
    private Context context;
    private boolean isrunning = true;
    String history;


    public ProcessingThread(Context context, String history) {
        this.context = context;
        this.history = history;
    }

    @Override
    public void run() {
        Log.d("SERVICE", "I am logging");

        Log.d("SERVICE", "I am still logging");
        sendMessage();
        sleep();
        while (isrunning){

        }

    }

    public void sendMessage(){

        String[] parts = history.split(",");
        for ( int i = 0; i < parts.length; i++){
            Intent intent = new Intent();
            intent.setAction("DIN SERVICIU");
            Log.d("SERVICE", parts[i].strip());
            intent.putExtra("message",parts[i].strip());
            context.sendBroadcast(intent);
            sleep();
        }

    }
    public void sleep(){
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stopThread(){
        isrunning = false;
    }
}
