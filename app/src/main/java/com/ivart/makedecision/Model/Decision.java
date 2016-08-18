package com.ivart.makedecision.Model;

public class Decision {

    private Long id;
    public String mDecisionName;

    public Decision() {
    }

    public Decision(String mDecisionName) {

        this.mDecisionName = mDecisionName;
    }

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
