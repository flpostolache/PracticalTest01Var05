package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {


    private Button verify_button;
    private Button cancel_button;
    private TextView view;

    private class Button_listen implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.verify:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    };

    private Button_listen listen = new Button_listen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);
        verify_button = findViewById(R.id.verify);
        cancel_button = findViewById(R.id.cancel);
        view = findViewById(R.id.textView2);
        verify_button.setOnClickListener(listen);
        cancel_button.setOnClickListener(listen);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.HISTORY)) {
            String numberOfClicks = intent.getStringExtra(Constants.HISTORY);
            view.setText(numberOfClicks);
        }
    }
}