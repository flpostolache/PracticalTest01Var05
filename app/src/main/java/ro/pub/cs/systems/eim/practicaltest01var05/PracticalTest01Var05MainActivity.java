package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private Button NaviateToSecondaryActivityButton;
    private Button top_left_button;
    private Button top_right_button;
    private Button bottom_left_button;
    private Button bottom_right_button;
    private Button center_button;

    private TextView history;

    private int buttons_pressed = 0;
    private int serviceStatus = 0;

    private IntentFilter intentFilter= new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    class Button_listen implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.top_left_button:
                    if (history.getText().toString().equals("")){
                        history.setText("Top Left");
                    }
                    else {
                        history.setText(history.getText().toString() + ", Top Left");
                    }
                    buttons_pressed++;
                    break;
                case R.id.top_right_button:
                    if (history.getText().toString().equals("")){
                        history.setText("Top Right");
                    }
                    else {
                        history.setText(history.getText().toString() + ", Top Right");
                    }
                    buttons_pressed++;
                    break;
                case R.id.center_button:
                    if (history.getText().toString().equals("")){
                        history.setText("Center");
                    }
                    else {
                        history.setText(history.getText().toString() + ", Center");
                    }
                    buttons_pressed++;
                    break;
                case R.id.bottom_left_button:
                    if (history.getText().toString().equals("")){
                        history.setText("Bottom Left");
                    }
                    else {
                        history.setText(history.getText().toString() + ", Bottom Left");
                    }
                    buttons_pressed++;
                    break;
                case R.id.bottom_right_button:
                    if (history.getText().toString().equals("")){
                        history.setText("Bottom Right");
                    }
                    else {
                        history.setText(history.getText().toString() + ", Bottom Right");
                    }
                    buttons_pressed++;
                    break;
                case R.id.button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                    String istoric = history.getText().toString();
                    intent.putExtra(Constants.HISTORY, istoric);
                    startActivityForResult(intent, Constants.REQUEST_CODE);
                    break;
            }
            if (buttons_pressed >= 5 && serviceStatus == 0){
                Log.d("DA", "AM Intrat sa pornim serviciul");
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                intent.putExtra(Constants.HISTORY, history.getText().toString());
                getApplicationContext().startService(intent);
                serviceStatus = 1;
            }
        }
    }
    private Button_listen listen = new Button_listen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);
        NaviateToSecondaryActivityButton = findViewById(R.id.button);
        top_left_button = findViewById(R.id.top_left_button);
        top_right_button = findViewById(R.id.top_right_button);
        bottom_left_button = findViewById(R.id.bottom_left_button);
        bottom_right_button = findViewById(R.id.bottom_right_button);
        center_button = findViewById(R.id.center_button);
        history = findViewById(R.id.history);
        NaviateToSecondaryActivityButton.setOnClickListener(listen);
        top_left_button.setOnClickListener(listen);
        top_right_button.setOnClickListener(listen);
        bottom_left_button.setOnClickListener(listen);
        bottom_right_button.setOnClickListener(listen);
        center_button.setOnClickListener(listen);

        intentFilter.addAction("DIN SERVICIU");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.BUTTON_PRESSED, buttons_pressed);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.BUTTON_PRESSED)) {
            buttons_pressed = savedInstanceState.getInt(Constants.BUTTON_PRESSED);
            Toast.makeText(this, "The saved value is: " + buttons_pressed, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int result_code, Intent intent) {
        super.onActivityResult(requestCode, result_code, intent);
        if (requestCode == Constants.REQUEST_CODE) {
            if (result_code == RESULT_OK)
                Toast.makeText(this, "The activity returned with result VERIFY", Toast.LENGTH_LONG).show();
            else if (result_code == RESULT_CANCELED)
                Toast.makeText(this, "The activity returned with result CANCEL", Toast.LENGTH_LONG).show();
            history.setText("");
            buttons_pressed = 0;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        serviceStatus = 0;
        super.onDestroy();

    }
}