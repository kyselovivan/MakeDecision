package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ivart.makedecision.Adapters.DescriptionListAdapter;
import com.ivart.makedecision.Model.CalculateDecison;
import com.ivart.makedecision.Model.DecisionDescription;
import com.ivart.makedecision.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditDescriptionListActivity extends Activity  {

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
        decisionId = intent.getLongExtra("idDecision", 0);
        square = intent.getIntExtra("squareNumber", 0);
        addDescription = (Button) findViewById(R.id.btn_add_description);
        clearDescriptions = (Button) findViewById(R.id.btn_clear_description);

        Toast.makeText(this, "Decision id =" + decisionId + "\n square = " + square, Toast.LENGTH_SHORT).show();

        realm = Realm.getDefaultInstance();
        RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class)
                .equalTo("decisionId", this.decisionId)
                .equalTo("square", this.square)
                .findAll();
        descriptionsList = (ListView) findViewById(R.id.listv_description_list);
        final DescriptionListAdapter descriptionListAdapter = new DescriptionListAdapter(this, results);
        descriptionsList.setAdapter(descriptionListAdapter);

        descriptionsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Long tempId = descriptionListAdapter.getRealmResults().get(position).getId();
                showDeleteSingleDescriptionDialog(tempId, square);
                descriptionsList.invalidateViews();
                return true;
            }
        });

        descriptionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long tempId = descriptionListAdapter.getRealmResults().get(position).getId();
                editDescription(tempId);
            }
        });

        clearDescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAllDescriptionDialog(decisionId, square);
            }
        });

        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSingleDescription(decisionId,square);
            }
        });

    }


    public void showDeleteAllDescriptionDialog(final long decisionId, final int square) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.delete_all_descriptions)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.delete_all, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class)
                                .equalTo("decisionId", decisionId)
                                .equalTo("square", square)
                                .findAll();
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

    public void showDeleteSingleDescriptionDialog(final long id, final int square) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.delete_single_description)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.delete_all, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class)
                                .equalTo("id", id)
                                .equalTo("square", square)
                                .findAll();
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

    public void editDescription(long descriptionId){
        Intent intent = new Intent(this, EditDescriptionActivity.class);
        intent.putExtra("descriptionId",descriptionId);
        startActivity(intent);
    }

    public void addSingleDescription(long decisionId, int square){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("editDecisionId",decisionId);
        intent.putExtra("editSquare",square);
        startActivity(intent);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        descriptionsList.invalidateViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.calculate) {
            CalculateDecison calculate = new CalculateDecison();
            double ifItHapp = calculate.getRaitingBySquare(decisionId,1);
            double ifItDoesnt = calculate.getRaitingBySquare(decisionId,2);
            double wontItItHapp = calculate.getRaitingBySquare(decisionId,3);
            double wontItItDoesnt = calculate.getRaitingBySquare(decisionId,4);
            double[] results = {ifItHapp,ifItDoesnt,wontItItHapp,wontItItDoesnt};
            Intent intent = new Intent(this, PieChartActivity.class);
            intent.putExtra("results",results);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

}
