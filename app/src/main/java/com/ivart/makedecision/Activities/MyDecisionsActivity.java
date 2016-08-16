package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import java.util.List;

/**
 * Created by Ivan on 8/16/2016.
 */
public class MyDecisionsActivity extends Activity{
    TextView myDecisions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_decisions);
        myDecisions = (TextView)findViewById(R.id.txtv_my_decisions);
        List<Decision> listDecisions = Decision.listAll(Decision.class);
        myDecisions.setText(listDecisions.toString());
    }
}
