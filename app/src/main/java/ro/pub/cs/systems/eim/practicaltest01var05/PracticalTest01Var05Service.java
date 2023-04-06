package ro.pub.cs.systems.eim.practicaltest01var05;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class PracticalTest01Var05Service extends Service {

    private ProcessingThread processingThread;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String history = intent.getStringExtra(Constants.HISTORY);
        processingThread = new ProcessingThread(this, history);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
