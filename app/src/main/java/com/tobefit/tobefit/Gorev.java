package com.tobefit.tobefit;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.tobefit.tobefit.Models.Exercise;
import com.tobefit.tobefit.Models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Gorev extends AppCompatActivity {

    @BindView(R.id.txt_set)
    TextView txt_set;

    @BindView(R.id.txt_info)
    TextView txt_info;

    @BindView(R.id.donut_progress)
    DonutProgress circleProgress;

    @BindView(R.id.stop)
    Button stop;

    @BindView(R.id.start)
    Button start;

    @BindView(R.id.CL_Finish)
    ConstraintLayout CL_Finish;

    @BindView(R.id.btn_Again)
    Button btn_Again;

    @BindView(R.id.btn_GoCalendar)
    Button btn_GoCalendar;



    boolean pause = true;

    public Exercise exercise;

    private  Boolean completed = false  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gorev);
        ButterKnife.bind(this);

        Intent i = getIntent();
        exercise = (Exercise) i.getSerializableExtra("exercise");

        CL_Finish.setVisibility(View.GONE);

        circleProgress.setProgress(100);

        /*final Handler handler = new Handler();
        final int delay = 1000; //milliseconds*/
/*
        final int zaman = 60000;
        final int[] gecenzaman = {0};

        final float dokulen ;
        dokulen = (zaman/delay)/60;
*/


        final int[] total = {100};
        final int[] p = {0};
        final int[] gecenzaman = {0};
        final int[] timer = {10};
        final int delay = 1000; //milliseconds
        final int StepSize = exercise.getSteps().size();
        final int[] inStep = {0};
        final boolean[] stepWaiting = {true};

        Step step = exercise.getSteps().get(inStep[0]);

        txt_set.setText(step.getId()+". Tekrar");
        txt_info.setText(step.getStepName());

        circleProgress.setProgress(total[0]);
        circleProgress.setText(step.getDuration()+"s");


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause = true;
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                pause = false;
                if(pause==false && stepWaiting[0] == true){
                    start.setVisibility(View.INVISIBLE);


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    public void run(){

                        Step step = exercise.getSteps().get(inStep[0]);
                        timer[0]=step.getDuration();
                        txt_set.setText(step.getId()+". Tekrar");
                        txt_info.setText(step.getStepName());
                        int times = timer[0] ;
                        int vale =100/times;

                        gecenzaman[0]+=1;
                        p[0]+=vale;
                        total[0] = 100-p[0];

                        Log.e("Vale Time:",vale+"");
                        Log.e("Times Time:",times+"");
                        Log.e("Total Time:",total[0]+"");


                        circleProgress.setProgress(total[0]);
                        circleProgress.setText((times-gecenzaman[0])+"s");

                        if(total[0]>0){

                            handler.postDelayed(this, delay);



                        }else{

                            if(inStep[0]< StepSize-1){
                                inStep[0]++;
                                stepWaiting[0] = true;
                                start.setVisibility(View.VISIBLE);
                                total[0] = 100;
                                 step = exercise.getSteps().get(inStep[0]);
                                timer[0]=step.getDuration();
                                txt_set.setText(step.getId()+". Tekrar");
                                txt_info.setText(step.getStepName());
                                p[0]=0;
                                gecenzaman[0] = 0;

                                circleProgress.setProgress(total[0]);
                                circleProgress.setText(step.getDuration()+"s");


                            }else{

                                start.setVisibility(View.GONE);
                                CL_Finish.setVisibility(View.VISIBLE);
                                Sessions sessions = new Sessions(view.getContext().getApplicationContext());
                                sessions.addEasyMode(2);
                            }

                        }



                    }
                }, delay);

                }
            }
        });









        btn_GoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }
}
