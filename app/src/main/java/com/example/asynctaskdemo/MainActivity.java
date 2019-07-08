package com.example.asynctaskdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener btn_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MyTask myTask  = new MyTask(MainActivity.this, lbl,btn );
            myTask.execute();
            btn.setEnabled(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialComponent();
    }

    private void InitialComponent() {

        lbl = findViewById(R.id.lbl);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(btn_click);
    }

    TextView lbl;
    Button btn;
}
