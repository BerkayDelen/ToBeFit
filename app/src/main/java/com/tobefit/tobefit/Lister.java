package com.tobefit.tobefit;

import com.tobefit.tobefit.Models.Exercise;
import com.tobefit.tobefit.Models.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 24.3.2018.
 */

public class Lister {

    public Exercise ExerciseRandom(){
        List<Exercise> exercises = new ArrayList<Exercise>();


        Step step =new Step(1,"Yerinde Koşma",10,4);
        Step step1 =new Step(2,"Yerinde Koşma",8,4);
        Step step2 =new Step(3,"Yerinde Koşma",5,4);
        List<Step> steps =new ArrayList<Step>();
        steps.add(step);
        steps.add(step1);
        steps.add(step2);
        Exercise exercise =new Exercise(1,"Basit Yerinde Koşma",steps);

        Step step20 =new Step(1,"Mekik",50,4);
        Step step21 =new Step(2,"Mekik",60,4);
        Step step22 =new Step(3,"Mekik",40,4);
        List<Step> steps2 =new ArrayList<Step>();
        steps2.add(step20);
        steps2.add(step21);
        steps2.add(step22);
        Exercise exercise2 =new Exercise(1,"Basit Mekik",steps2);

        Step step30 =new Step(1,"Şınav",30,4);
        Step step31 =new Step(2,"Şınav",40,4);
        Step step32 =new Step(3,"Şınav",30,4);
        List<Step> steps3 =new ArrayList<Step>();
        steps3.add(step30);
        steps3.add(step31);
        steps3.add(step32);
        Exercise exercise3 =new Exercise(1,"Basit Şınav",steps3);

        exercises.add(exercise);
        exercises.add(exercise2);
        exercises.add(exercise3);

        Random rand = new Random();

        int  n = rand.nextInt(3);
        return  exercises.get(n);
    }
}
