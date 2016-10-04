package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ivart.makedecision.Adapters.DecisionListAdapter;
import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.Model.DecisionDescription;
import com.ivart.makedecision.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ivan on 8/16/2016.
 */
public class MyDecisionsActivity extends Activity {

    Realm realm;
    ListView decisionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_decisions);
        realm = Realm.getDefaultInstance();
        decisionList = (ListView) findViewById(R.id.listv_decision_list);
        RealmResults<Decision> results = realm.where(Decision.class).findAll();
        if(results.isEmpty()){
            setContentView(R.layout.empty_decision_list);
        }
        else{
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

        }
    }

    public void showDeleteAllDecisionsDialog() {
        RealmResults<Decision> resultsDecision = realm.where(Decision.class).findAll();
        if (resultsDecision.isEmpty()) {
            Toast.makeText(MyDecisionsActivity.this, R.string.noDecisions, Toast.LENGTH_SHORT).show();
        } else {
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
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                        realm.deleteAll();
                                }
                            });
                            setContentView(R.layout.empty_decision_list);
                        }
                    });
            dialog.create();
            dialog.show();
        }
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
                .setPositiveButton(R.string.delete_one, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                RealmResults<Decision> results = realm.where(Decision.class).equalTo("id", id).findAll();
                                results.deleteAllFromRealm();
                            }
                        });
                    }
                })
                .setNeutralButton(R.string.edit_decision_name, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                Decision result = realm.where(Decision.class).equalTo("id", id).findFirst();
                                String name = result.getmDecisionName();
                                Intent intent = new Intent(MyDecisionsActivity.this, MakeDecisionActivity.class);
                                Long resultId = result.getId();
                                intent.putExtra("editingId", resultId);
                                intent.putExtra("editName",name);
                                startActivity(intent);
                            }
                        });
                    }
                });

        dialog.create();
        dialog.show();
    }

    public void onClick(View view) {
        showDeleteAllDecisionsDialog();
    }
}
