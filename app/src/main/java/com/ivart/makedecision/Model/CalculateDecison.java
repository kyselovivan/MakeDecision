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


    public float getSummaryRaitingByDecisionId(long id){
        float temp = 0;
        RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class).equalTo("decisionId",id).findAll();
        for(int i=0;i<results.size();i++){
            temp += results.get(i).getRaiting();
        }
        return temp;
    }

    public double getRaitingBySquare(long id, int square){
        float temp = 0;
        RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class)
                .equalTo("decisionId",id)
                .equalTo("square",square).findAll();
        for(int i=0;i<results.size();i++){
            temp += results.get(i).getRaiting();
        }
        float result = temp*100/getSummaryRaitingByDecisionId(id);
        return result;
    }
}
