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
    public CalculateDecison() {
        realm = Realm.getDefaultInstance();
    }


    public float getSummaryRaitingByDecisionId(long id){
        float temp = 0;
        RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class).equalTo("decisionId",id).findAll();
        for(int i=0;i<results.size();i++){
            float res= results.get(i).getRaiting();
            if(res == 1){
                temp+=0.1;
            }
            else if(res == 2){
                temp+=0.25;
            }
            else if(res == 3){
                temp+=0.5;
            }
            else if(res == 4){
                temp+=0.75;
            }
            else if(res == 5){
                temp+=1;
            }
        }
        return temp;
    }

    public double getRaitingBySquare(long id, int square){
        float temp = 0;
        RealmResults<DecisionDescription> results = realm.where(DecisionDescription.class)
                .equalTo("decisionId",id)
                .equalTo("square",square).findAll();
        if(results.isEmpty()){
            return 0;
        }
        else {
            for (int i = 0; i < results.size(); i++) {
                float res = results.get(i).getRaiting();
                if (res == 1) {
                    temp += 0.1;
                } else if (res == 2) {
                    temp += 0.25;
                } else if (res == 3) {
                    temp += 0.5;
                } else if (res == 4) {
                    temp += 0.75;
                } else if (res == 5) {
                    temp += 1;
                }
            }
            double result = temp * 100 / getSummaryRaitingByDecisionId(id);
            return result;
        }
    }
}
