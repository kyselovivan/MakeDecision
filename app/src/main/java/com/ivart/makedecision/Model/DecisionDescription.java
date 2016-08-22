package com.ivart.makedecision.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ivan on 8/23/2016.
 */

public class DecisionDescription  extends RealmObject {
    @PrimaryKey
    Long id;

    Long decisionId;
    int square;
    String descriptionText;

    public DecisionDescription() {
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(Long decisionId) {
        this.decisionId = decisionId;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    @Override
    public String toString() {
        return "DecisionDescription{" +
                "id=" + id +
                ", decisionId=" + decisionId +
                ", square=" + square +
                ", descriptionText='" + descriptionText + '\'' +
                '}';
    }
}
