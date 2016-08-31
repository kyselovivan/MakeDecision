package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ivart.makedecision.Adapters.DecisionListAdapter;
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ivan on 8/16/2016.
 */
public class MyDecisionsActivity extends Activity{

    TextView myDecisions;
    Button clearDecisions;
    Realm realm;
    ListView decisionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_decisions);
        clearDecisions = (Button)findViewById(R.id.btn_clear_decisions);
        realm = Realm.getDefaultInstance();
        decisionList = (ListView)findViewById(R.id.listv_decision_list);
        RealmResults<Decision> results = realm.where(Decision.class).findAll();
        DecisionListAdapter decisionListAdapter = new DecisionListAdapter(this,results);
        decisionList.setAdapter(decisionListAdapter);


        clearDecisions.setOnClickListener(new View.OnClickListener() {
            RealmResults<Decision> results = realm.where(Decision.class).findAll();
            @Override
            public void onClick(View v) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        results.deleteAllFromRealm();
                    }
                });
            }
        });
    }

}
