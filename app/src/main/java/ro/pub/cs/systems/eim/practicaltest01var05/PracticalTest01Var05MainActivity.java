package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
}