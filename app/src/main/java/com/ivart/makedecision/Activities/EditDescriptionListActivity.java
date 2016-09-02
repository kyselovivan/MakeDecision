package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ivart.makedecision.Adapters.DescriptionListAdapter;
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.Model.DecisionDescription;
import com.ivart.makedecision.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditDescriptionListActivity extends Activity implements View.OnClickListener {

    Long decisionId;
    int square;
    Button addDescription;
    Button clearDescriptions;
    ListView descriptionsList;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_description_list);
        Intent intent = getIntent();
        decisionId = intent.getLongExtra("idDecision",0);
        square = intent.getIntExtra("squareNumber",0);
        addDescription = (Button)findViewById(R.id.btn_add_description);
        clearDescriptions = (Button)findViewById(R.id.btn_clear_description);

        Toast.makeText(this,"Decision id ="+decisionId+"\n square = "+ square,Toast.LENGTH_SHORT).show();

        realm = Realm.getDefaultInstance();
        RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class)
                .equalTo("decisionId",this.decisionId)
                .equalTo("square",this.square)
                .findAll();
        descriptionsList = (ListView)findViewById(R.id.listv_description_list);
        final DescriptionListAdapter descriptionListAdapter = new DescriptionListAdapter(this,results);
        descriptionsList.setAdapter(descriptionListAdapter);

    }

    @Override
    public void onClick(View v) {

    }
}
