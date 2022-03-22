package com.chen.aptdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.chen.lib_annotations.BindView;
import com.chen.lib_bindview.Binding;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.layout)
    ConstraintLayout layout;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);

        layout.setBackgroundColor(Color.CYAN);
        textView.setText("Hello Chen");
    }
}