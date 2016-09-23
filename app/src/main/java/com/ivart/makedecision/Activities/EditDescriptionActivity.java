package com.ivart.makedecision.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.ivart.makedecision.Model.DecisionDescription;
import com.ivart.makedecision.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditDescriptionActivity extends Activity {

    Realm realm;
    EditText editTextDescription;
    RatingBar editRaitBar;
    Long descriptionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_description);
        Intent intent = getIntent();
        descriptionId = intent.getLongExtra("descriptionId",0);
        realm = Realm.getDefaultInstance();
        final RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class)
                .equalTo("id",descriptionId)
                .findAll();
        DecisionDescription textToEdit = realm.where(DecisionDescription.class).equalTo("id",descriptionId).findFirst();
        String tempText = textToEdit.getDescriptionText();
        editTextDescription = (EditText)findViewById(R.id.edt_edit_decision_description);
        editRaitBar = (RatingBar)findViewById(R.id.edit_raiting_bar);
        editTextDescription.setText(tempText);

    }


    public void updateDescription(Realm realm){
        DecisionDescription toEdit = realm.where(DecisionDescription.class)
                .equalTo("id",descriptionId).findFirst();
        realm.beginTransaction();
        toEdit.setDescriptionText(editTextDescription.getText().toString());
        toEdit.setRaiting(editRaitBar.getRating());
        realm.commitTransaction();

    }


    public void onClick(View view) {
        updateDescription(realm);
        EditDescriptionActivity.this.finish();
    }
}
