package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ivart.makedecision.Model.CalculateDecison;
import com.ivart.makedecision.R;

/**
 * Created by Ivan on 8/16/2016.
 */
public class AboutUsActivity extends Activity{

    CalculateDecison calculate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        calculate = new CalculateDecison();
        Toast.makeText(this,""+calculate.getSummaryRaiting(),Toast.LENGTH_LONG).show();
    }
}
