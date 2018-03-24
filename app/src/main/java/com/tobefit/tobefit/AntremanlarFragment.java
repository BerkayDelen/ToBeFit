package com.tobefit.tobefit;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 18.3.2018.
 */

public class AntremanlarFragment extends Fragment {


    @BindView(R.id.easyMode)
     LinearLayout easyMode;

    @BindView(R.id.mediumMode)
     LinearLayout mediumMode;

    @BindView(R.id.hardMode)
     LinearLayout hardMode;



    @BindView(R.id.img_easy)
    ImageView img_easy;
    @BindView(R.id.img_medium)
    ImageView img_medium;
    @BindView(R.id.img_hard)
    ImageView img_hard;

    @BindView(R.id.prgs_easy)
    ProgressBar prgs_easy;

    @BindView(R.id.prgs_medium)
    ProgressBar prgs_medium;

    @BindView(R.id.prgs_hard)
    ProgressBar prgs_hard;


    @BindView(R.id.prgs_txt_easy)
    TextView prgs_text_easy;

    @BindView(R.id.prgs_txt_medium)
    TextView prgs_txt_medium;

    @BindView(R.id.prgs_txt_hard)
    TextView prgs_txt_hard;



    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    Boolean lock_E = false;
    Boolean lock_M = true;
    Boolean lock_H = true;

    Context context;
    Sessions sessions;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.antremanlar_fragment,container,false);
        ButterKnife.bind(this,view);
        context = view.getContext();
        sessions = new Sessions(context.getApplicationContext());

        if(lock_E==false)
        easyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModeSelect(view);
                int progress_e =  sessions.getEasyMode();
                if(progress_e == -1){
                    sessions.updateEasyMode(0);
                    getEastMode();
                }

            }
        });

        if(lock_M==false)
        mediumMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModeSelect(view);
            }
        });
        if(lock_H==false)
        hardMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModeSelect(view);
            }
        });

        //preferences için bir nesne tanımlıyorum.

        //preferences içerisine bilgi girmek için tanımlama
        preferences= PreferenceManager.getDefaultSharedPreferences(view.getContext());
        editor = preferences.edit();

        getMode();
        getEastMode();

        return  view;
    }

    private  void getEastMode(){

        int progress_e =  sessions.getEasyMode();
        if(progress_e != -1){
            prgs_easy.setVisibility(View.VISIBLE);
            prgs_easy.setProgress(progress_e);
            prgs_text_easy.setText("%"+progress_e);
        }else{
            prgs_easy.setProgress(0);
            prgs_easy.setVisibility(View.GONE);
            prgs_text_easy.setText(" Hemen Başla ");
        }


    }

    public void ModeSelect(View v){
        if(v.getId() == easyMode.getId()){
            editor.putString("Mode", "Easy");

        }else if(v.getId() == mediumMode.getId()){
            editor.putString("Mode", "Medium");


        }else if(v.getId() == hardMode.getId()){
            editor.putString("Mode", "Hard");

        }else{
            editor.putString("Mode", "None");

        }

        editor.commit();

        String ModeInfo =preferences.getString("Mode", "None0");

        getMode();
    }
    private void getMode(){

        String ModeInfo =preferences.getString("Mode", "None0");
        if(ModeInfo.equals("Easy")){
            img_easy.setImageResource(R.drawable.star_one_s);
            img_medium.setImageResource(R.drawable.star_two);
            img_hard.setImageResource(R.drawable.star_tree);
        }else if(ModeInfo.equals("Medium")){
            img_easy.setImageResource(R.drawable.star_one);
            img_medium.setImageResource(R.drawable.star_two_s);
            img_hard.setImageResource(R.drawable.star_tree);

        }else if(ModeInfo.equals( "Hard")){
            img_easy.setImageResource(R.drawable.star_one);
            img_medium.setImageResource(R.drawable.star_two);
            img_hard.setImageResource(R.drawable.star_tree_s);
        }else{
            img_easy.setImageResource(R.drawable.star_one);
            img_medium.setImageResource(R.drawable.star_two);
            img_hard.setImageResource(R.drawable.star_tree);
        }

       // Toast.makeText(context,ModeInfo,Toast.LENGTH_SHORT).show();
    }
}
