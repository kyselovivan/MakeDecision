package com.ivart.makedecision.Model;

import android.util.Log;

import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ivan on 9/7/2016.
 */

public class CalculateDecison {
    Realm realm;
    long whatWillBeIfItHappens;
    long whatWillBeIfItDoesntHappen;
    long whatWontBeIfItHappens;
    long whatWontBeIfItDoesntHappen;

    public CalculateDecison() {
        realm = Realm.getDefaultInstance();
    }

    public void getSummaryRaiting(){
        RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class).findAll();
        for(int i=0;i<results.size();i++){
            DecisionDescription temp = results.get(i);
            Log.d("",temp.toString());
        }
    }


}
