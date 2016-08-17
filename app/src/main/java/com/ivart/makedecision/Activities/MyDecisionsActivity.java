package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import java.util.List;

/**
 * Created by Ivan on 8/16/2016.
 */
public class MyDecisionsActivity extends Activity{

    TextView myDecisions;
    Button clearDecisions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_decisions);
        myDecisions = (TextView)findViewById(R.id.txtv_my_decisions);
        clearDecisions = (Button)findViewById(R.id.btn_clear_decisions);
        displayDesiosions();

        clearDecisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Decision.deleteAll(Decision.class);
                myDecisions.setText("");
            }
        });
    }

    private void displayDesiosions() {
        List<Decision> listDecisions = Decision.listAll(Decision.class);
        myDecisions.setText(listDecisions.toString());
    }
}
