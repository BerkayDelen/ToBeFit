package com.tobefit.tobefit.Models;

import java.util.List;
import java.io.Serializable;

/**
 * Created by Admin on 24.3.2018.
 */


public class Exercise implements Serializable {
    private  int id;
    private  String Name;
    private List<Step> Steps;


    public Exercise(int id, String name, List<Step> steps) {
        this.id = id;
        Name = name;
        Steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Step> getSteps() {
        return Steps;
    }

    public void setSteps(List<Step> steps) {
        Steps = steps;
    }


}
