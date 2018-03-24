package com.tobefit.tobefit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    private FragmentManager fragmentManager;

    @BindView(R.id.txt_Boy_toolbar)
    public  TextView txt_Boy_toolbar;

    @BindView(R.id.txt_Kilo_toolbar)
    public  TextView txt_Kilo_toolbar;

    @BindView(R.id.txt_VKE_toolbar)
    public  TextView txt_VKE_toolbar;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    FragmentTransaction  fragmentTransaction0= fragmentManager.beginTransaction();
                    AntremanlarFragment antremanlarFragment =new AntremanlarFragment();

                    fragmentTransaction0.replace(R.id.ContainerFrame,antremanlarFragment,"Antremanlar");
                    fragmentTransaction0.commit();
                    return true;
                case R.id.navigation_dashboard:


                    TakvimFragment takvimFragment =new TakvimFragment();
                    FragmentTransaction  fragmentTransaction1= fragmentManager.beginTransaction();
                    fragmentTransaction1.replace(R.id.ContainerFrame,takvimFragment,"Takvim");
                    fragmentTransaction1.commit();

                    return true;
                case R.id.navigation_notifications:


                    ProfileFragment profileFragment =new ProfileFragment();
                    FragmentTransaction  fragmentTransaction2= fragmentManager.beginTransaction();
                    fragmentTransaction2.replace(R.id.ContainerFrame,profileFragment,"Profil");
                    fragmentTransaction2.commit();



                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager=getFragmentManager();


        FragmentTransaction  fragmentTransactionMain= fragmentManager.beginTransaction();
        AntremanlarFragment antremanlarFragment =new AntremanlarFragment();

        fragmentTransactionMain.add(R.id.ContainerFrame,antremanlarFragment,"Antremanlar");
        fragmentTransactionMain.commit();

        getVKE(getApplicationContext(),txt_Boy_toolbar,txt_Kilo_toolbar,txt_VKE_toolbar);



    }



    public  void getVKE(Context c,TextView txt_Boy,TextView txt_Kilo, TextView txt_VKE){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = preferences.edit();

        String Boy =preferences.getString("Boy", "-1");
        String Kilo =preferences.getString("Kilo", "-1");
        Log.e("Boy",Boy);

        try{
            if(Boy.toString().trim().length() == 0){
                Boy="-1";
            }else{
                Boy =  Boy.toString();
            }
            if(Kilo.toString().trim().length() == 0){
                Kilo="-1";
            }else{
                Kilo =  Kilo.toString();
            }
        }catch (Exception e){
            Boy="-1";
            Kilo="-1";
        }

        int Boycm = Integer.parseInt(Boy);
        int Kilo_int = Integer.parseInt(Kilo);
        if(Boycm != -1 && Kilo_int != -1){
            txt_Boy.setText("Boy : "+Boy+" cm");
            txt_Kilo.setText("Kilo : "+Kilo+" kg");
            Log.e("Boy",""+(Float.parseFloat(Boy.substring(0,1)+"."+Boy.substring(1,3))));
            float VKE = (Kilo_int/((Float.parseFloat(Boy.substring(0,1)+"."+Boy.substring(1,3)))*(Float.parseFloat(Boy.substring(0,1)+"."+Boy.substring(1,3)))));

            if(VKE >= 0 && VKE <= 18.4){
                txt_VKE.setText("VKE : "+String.valueOf(VKE).substring(0,4)+" - Zayıf");
            }else if(VKE >= 18.5  && VKE <= 24.9){
                txt_VKE.setText("VKE : "+String.valueOf(VKE).substring(0,4)+" - Normal");
            }
            else if(VKE >= 25.0  && VKE <= 29.9){
                txt_VKE.setText("VKE : "+String.valueOf(VKE).substring(0,4)+" - Fazla Kilolu");
            }
            else if(VKE >= 30.0 && VKE <= 34.9){
                txt_VKE.setText("VKE : "+String.valueOf(VKE).substring(0,4)+" - Obez I");
            }
            else if(VKE >= 35.0 && VKE <= 44.9){
                txt_VKE.setText("VKE : "+String.valueOf(VKE).substring(0,4)+" - Obez II");
            }
            else if(VKE >= 45.0){
                txt_VKE.setText("VKE : "+String.valueOf(VKE).substring(0,4)+" - Aşırı Obez III");
            }else{
                txt_VKE.setText("");
            }
        }else{
            txt_Boy.setText("");
            txt_Kilo.setText("");
            txt_VKE.setText("");
        }
    }

    @Override
    public void onBackPressed() {


    }
}
