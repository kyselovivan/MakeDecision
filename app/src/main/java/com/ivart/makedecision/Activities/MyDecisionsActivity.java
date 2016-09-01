package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        final DecisionListAdapter decisionListAdapter = new DecisionListAdapter(this,results);
        decisionList.setAdapter(decisionListAdapter);

        decisionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long tempId = decisionListAdapter.getRealmResults().get(position).getId();
                Toast.makeText(MyDecisionsActivity.this,""+tempId,Toast.LENGTH_SHORT).show();
            }
        });


        clearDecisions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });
    }

    public void showDeleteDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Delete decisions")
                .setCancelable(false)
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final RealmResults<Decision> results = realm.where(Decision.class).findAll();
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
