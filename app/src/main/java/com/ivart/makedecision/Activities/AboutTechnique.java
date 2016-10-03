package com.ivart.makedecision.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.ivart.makedecision.R;

public class AboutTechnique extends AppCompatActivity {
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_technique);
        mTextView = (TextView) findViewById(R.id.textView_about_technique);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
    }
}
