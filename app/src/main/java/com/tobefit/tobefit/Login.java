package com.tobefit.tobefit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.pinball83.maskededittext.MaskedEditText;

import butterknife.BindView;

public class Login extends AppCompatActivity {
    private static final String TAG = "Status";
    private static int stopPosition = 0;
    VideoView videoview;
    private Boolean inLogin = true;

    TextInputLayout layout_FullName_register;
    TextInputLayout layout_email_register;
    TextInputLayout layout_password_register;
    TextInputLayout layout_password_register_re;

    TextInputLayout input_layout_email;
    TextInputLayout input_layout_password;

    EditText input_layout_FullName_register;
    EditText input_email_register;
    EditText input_password_register;
    EditText input_password_register_re;


    MaskedEditText Boy;


    EditText Kilo;



    TextView txt_LoginRegister;

    EditText input_email;
    EditText input_password;
    CheckBox rememberMe;

    ConstraintLayout RegisterLayout;
    ConstraintLayout LoginLayout;

    Button Register_btn;

    String RememberMe_ses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        videoview = (VideoView) findViewById(R.id.LoginBackVideo);//Video yu tanımlıyoruz Viewdeki viedo ile eşleştiriyoruz
        RegisterLayout   = (ConstraintLayout) findViewById(R.id.RegisterLayout) ;
        LoginLayout      = (ConstraintLayout) findViewById(R.id.LoginLayout) ;

        /*Launcher*/
        final ConstraintLayout MainLauncherLayout =(ConstraintLayout) this.findViewById(R.id.LauncherLayout);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        RememberMe_ses = preferences.getString("rememberMe", "-1");



        final int mShortAnimationDuration = 750;
        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        MainLauncherLayout.setAlpha(1f);
        MainLauncherLayout.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                if(RememberMe_ses.equals("true")){
                    Intent goMainPage =new Intent(getApplicationContext(),MainActivity.class);
                    goMainPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(goMainPage);
                }else{
                    MainLauncherLayout.animate()
                            .alpha(0f)
                            .setDuration(mShortAnimationDuration)
                            .setListener(null);
                }


            }
        }, 2000);   //5 seconds


        /*Launcher*/

        /*Başlangıç Ayarları*/

        LoginLayout.setVisibility(View.VISIBLE);
        RegisterLayout.setVisibility(View.GONE);

        /*Başlangıç Ayarları*/

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.backvideo);//Raw dosyası altındaki video yu uri sini alıyoruz
        videoview.setVideoURI(uri);//aldığımız url yi video play a aktarıyoruz

        videoview.start();//videoyu başlatıyoruz

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0, 0);
            }
        });
         input_layout_email  = (TextInputLayout) findViewById(R.id.input_layout_email);
         input_layout_password  = (TextInputLayout) findViewById(R.id.input_layout_password);
         rememberMe = (CheckBox) findViewById(R.id.rememberMe);


          input_email      = (EditText) findViewById(R.id.input_email);
          input_password   = (EditText) findViewById(R.id.input_password);
        input_password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {//Done a Basıldıysa
                    Login();
                    return true;
                }
                return false;
            }
        });

        Button Login_btn =(Button) findViewById(R.id.Login_btn);
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

         txt_LoginRegister  = (TextView) findViewById(R.id.txt_LoginRegister);


        txt_LoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inLogin){
                    RegisterLayout.setVisibility(View.VISIBLE);
                    LoginLayout.setVisibility(View.GONE);
                    txt_LoginRegister.setText("Üye Girişi");
                    inLogin=false;
                }else{
                    RegisterLayout.setVisibility(View.GONE);
                    LoginLayout.setVisibility(View.VISIBLE);
                    txt_LoginRegister.setText("Üye Ol");
                    inLogin=true;
                }

            }
        });



        /*Register */
         layout_FullName_register       = (TextInputLayout) findViewById(R.id.input_layout_FullName_register);
         layout_email_register          = (TextInputLayout) findViewById(R.id.input_layout_email_register);
         layout_password_register       = (TextInputLayout) findViewById(R.id.input_layout_password_register);
         layout_password_register_re    = (TextInputLayout) findViewById(R.id.input_layout_password_register_re);



         input_layout_FullName_register   = (EditText) findViewById(R.id.input_FullName_register);
         input_email_register             = (EditText) findViewById(R.id.input_email_register);
         input_password_register          = (EditText) findViewById(R.id.input_password_register);
         input_password_register_re       = (EditText) findViewById(R.id.input_password_register_re);
         Boy                              =(MaskedEditText) findViewById(R.id.input_boy_register);
         Kilo                             =(EditText) findViewById(R.id.input_kilo_register);

         Register_btn = (Button) findViewById(R.id.Register_btn);


        input_password_register_re.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {//Done a Basıldıysa
                    if(input_password_register.equals(input_password_register_re)){
                        isEmptyError(layout_password_register_re,input_password_register_re,"Şifreler Uyuşmadı.");
                        return false;
                    }else{
                        Register();
                        return true;
                    }


                }
                return false;
            }
        });

        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        /*Register*/



    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition = videoview.getCurrentPosition(); //stopPosition is an int
        videoview.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        videoview.seekTo(stopPosition);
        videoview.start(); //Or use resume() if it doesn't work. I'm not sure
    }


    private boolean InputsControl(){
        boolean durum = true;

        if(isEmpty(input_layout_FullName_register)){
            layout_FullName_register.setError("Ad Soyad Boş Geçilemez");
            durum=false;
        }else{
            layout_FullName_register.setError(null);
        }

        if(isEmpty(input_email_register)){
            layout_email_register.setError("Email Boş Geçilemez");
            durum=false;
        }else{
            layout_email_register.setError(null);
        }

        if(isEmpty(input_password_register)){
            layout_password_register.setError("Şifre Boş Geçilemez");
            durum=false;
        }else{
            layout_password_register.setError(null);
        }

        if(isEmpty(input_password_register_re)){
            layout_password_register_re.setError("Şifre (Tekrar) Boş Geçilemez");
            durum=false;
        }else{
            layout_password_register_re.setError(null);
        }

        if(new String(String.valueOf(input_password_register.getText())).equals(new String(String.valueOf(input_password_register_re.getText())))){
            layout_password_register.setError(null);
            layout_password_register_re.setError(null);
        }else{

            layout_password_register.setError("Şifreler Uyuşmuyor. ");
            layout_password_register_re.setError("Şifreler Uyuşmuyor. ");
            durum=false;

        }



        return durum;


    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public boolean isEmptyError(TextInputLayout textInputLayout,EditText editText,String error){
        if(isEmpty(editText)){
            textInputLayout.setError(error);
            return false;
        }else{
            textInputLayout.setError(null);
            return true;
        }
    }

    private void Login(){
        if(
        isEmptyError(input_layout_email,input_email,"Email Boş Geçilemez") &&
        isEmptyError(input_layout_password,input_password,"Şifre Boş Geçilemez")
        ){
            SharedPreferences preferences2= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor2 = preferences2.edit();

            String Email =preferences2.getString("Email", "-1");
            String Password =preferences2.getString("Password", "-1");

            if(input_email.getText().toString().equals(Email) && input_password.getText().toString().equals(Password)){
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();

                if(rememberMe.isChecked()){
                    editor.putString("rememberMe", "true");
                }else{
                    editor.putString("rememberMe", "false");
                }

                editor.commit();
                Toast.makeText(getApplicationContext(),"Üye Girişi Başarılı",Toast.LENGTH_LONG).show();
                Intent goMainPage =new Intent(getApplicationContext(),MainActivity.class);
                goMainPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(goMainPage);
                finish();
            }else{
                input_layout_email.setError("Email Hatalı");
                input_layout_password.setError("Şifre Hatalı");
            }



        }

    }


    private boolean  Register(){
        if(InputsControl()){
            RegisterLayout.setVisibility(View.GONE);
            LoginLayout.setVisibility(View.VISIBLE);
            txt_LoginRegister.setText("Üye Ol");
            inLogin=true;

            input_email.setText(input_email_register.getText());
            input_password.setText(input_password_register.getText());
            String Boy_s="";
            try{
                if(Boy.getUnmaskedText().toString().trim().length() == 0){
                    Boy_s="-1";
                }else{
                    Boy_s =  Boy.getUnmaskedText().toString();
                }
            }catch (Exception e){
                Boy_s="-1";
            }


            String Kilo_s="";
            if(isEmpty(Kilo)){
                Kilo_s="-1";
            }else{
                Kilo_s =  Kilo.getText().toString();
            }


            UserDataSave(input_layout_FullName_register.getText().toString(),
                    input_email_register.getText().toString(),
                    input_password_register.getText().toString(),
                    Boy_s,
                    Kilo_s);

            input_layout_FullName_register.setText("");
            input_email_register.setText("");
            input_password_register.setText("");
            input_password_register_re.setText("");
            Boy.setText("");
            Kilo.setText("");
            Toast.makeText(getApplicationContext(),"Başarılı Bir Şekilde Kayıt Oldunuz. ",Toast.LENGTH_LONG).show();

            return true;
        }else{
            return false;
        }

    }

    public void UserDataSave(String FullName,String Email,String Password,String Boy,String Kilo){

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Boy", Boy);
        editor.putString("Kilo", Kilo);

        editor.putString("FullName", FullName);
        editor.putString("Email", Email);
        editor.putString("Password", Password);


        editor.commit();

    }


}
