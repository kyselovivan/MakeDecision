package com.ivart.makedecision.Model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Ivan on 8/16/2016.
 */

public class Decision extends SugarRecord {
    @Table
    private Long id;
    public String mDecisionName;

    public Decision() {
    }

    public Decision(String mDecisionName) {

        this.mDecisionName = mDecisionName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getmDecisionName() {

        return mDecisionName;
    }

    public void setmDecisionName(String mDecisionName) {
        this.mDecisionName = mDecisionName;
    }

    @Override
    public String toString() {
        return "Decision{" +
                "id=" + id +
                ", mDecisionName='" + mDecisionName + '\'' +
                '}';
    }
}
