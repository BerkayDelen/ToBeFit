package com.tobefit.tobefit.Models;

import java.io.Serializable;

/**
 * Created by Admin on 24.3.2018.
 */

public class Step implements Serializable {

    private int id;
    private String StepName;
    private int Duration;
    private int Break;


    public Step(int id, String stepName, int duration, int aBreak) {
        this.id = id;
        StepName = stepName;
        Duration = duration;
        Break = aBreak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStepName() {
        return StepName;
    }

    public void setStepName(String stepName) {
        StepName = stepName;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public int getBreak() {
        return Break;
    }

    public void setBreak(int aBreak) {
        Break = aBreak;
    }
}
