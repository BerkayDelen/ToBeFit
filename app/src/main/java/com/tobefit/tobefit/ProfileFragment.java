package com.tobefit.tobefit;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 18.3.2018.
 */

public class ProfileFragment extends Fragment {

    @BindView(R.id.exit)
    Button exit;

    @BindView(R.id.edit_FullName)
    EditText edit_FullName;

    @BindView(R.id.edit_Email)
    EditText edit_Email;

    @BindView(R.id.edit_Password)
    EditText edit_Password;

    @BindView(R.id.edit_Boy)
    MaskedEditText edit_Boy;

    @BindView(R.id.edit_Kilo)
    EditText edit_Kilo;

    @BindView(R.id.SaveChanges)
    Button SaveChanges;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
        ButterKnife.bind(this,view);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(view.getContext());
        SharedPreferences.Editor editor = preferences.edit();
        String FullName =preferences.getString("FullName", "-1");
        String Email =preferences.getString("Email", "-1");
        String Password =preferences.getString("Password", "-1");
        String Boy =preferences.getString("Boy", "-1");
        String Kilo =preferences.getString("Kilo", "-1");

        edit_FullName.setText(FullName);
        edit_Email.setText(Email);
        edit_Password.setText(Password);
        edit_Boy.setMaskedText(Boy);
        edit_Kilo.setText(Kilo);

        SaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = preferences.edit();




                editor.putString("Boy", edit_Boy.getUnmaskedText().toString());
                editor.putString("Kilo", edit_Kilo.getText().toString());

                editor.putString("FullName", edit_FullName.getText().toString());
                editor.putString("Email", edit_Email.getText().toString());
                editor.putString("Password", edit_Password.getText().toString());



                editor.commit();
                TextView txt_Boy_toolbar = (TextView) getActivity().findViewById(R.id.txt_Boy_toolbar);
                TextView txt_Kilo_toolbar = (TextView) getActivity().findViewById(R.id.txt_Kilo_toolbar);
                TextView txt_VKE_toolbar = (TextView) getActivity().findViewById(R.id.txt_VKE_toolbar);

                MainActivity mainActivity =new MainActivity();


                mainActivity.getVKE(view.getContext(),txt_Boy_toolbar,txt_Kilo_toolbar,txt_VKE_toolbar);
                Toast.makeText(view.getContext(),"Değişiklikler Kayıt edildi.",Toast.LENGTH_LONG).show();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = preferences.edit();



                editor.putString("rememberMe", "false");
                editor.commit();

                Intent intent = new Intent(view.getContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();



            }
        });
        return  view;
    }
}
