package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ivart.makedecision.Adapters.DecisionListAdapter;
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ivan on 8/16/2016.
 */
public class MyDecisionsActivity extends Activity {

    TextView myDecisions;
    Button clearDecisions;
    Realm realm;
    ListView decisionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_decisions);
        clearDecisions = (Button) findViewById(R.id.btn_clear_decisions);
        realm = Realm.getDefaultInstance();
        decisionList = (ListView) findViewById(R.id.listv_decision_list);
        RealmResults<Decision> results = realm.where(Decision.class).findAll();
        final DecisionListAdapter decisionListAdapter = new DecisionListAdapter(this, results);
        decisionList.setAdapter(decisionListAdapter);
        decisionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long tempId = decisionListAdapter.getRealmResults().get(position).getId();
                Intent intent = new Intent(MyDecisionsActivity.this, DecisionEditActivity.class);
                intent.putExtra("decisionId", tempId);
                startActivity(intent);
            }
        });

        decisionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Long tempId = decisionListAdapter.getRealmResults().get(position).getId();
                showDeleteOneDecision(tempId);
                decisionList.invalidateViews();
                return true;
            }
        });

        clearDecisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAllDeccisionsDialog();
            }
        });
    }

    public void showDeleteAllDeccisionsDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.delete_all_decisions)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.delete_all, new DialogInterface.OnClickListener() {
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
        dialog.create();
        dialog.show();
    }

    public void showDeleteOneDecision(final long id) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.delete_description)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.delete_all, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                RealmResults<Decision> results = realm.where(Decision.class).equalTo("id",id).findAll();
                                results.deleteAllFromRealm();
                            }
                        });
                    }
                });
        dialog.create();
        dialog.show();
    }

}
