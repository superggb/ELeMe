package com.example.ggb.eleme.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ggb.eleme.R;

/**
 * Created by GGB on 2017/12/6.
 */

public class login_fragment extends Fragment {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_login,container,false);
        accountEdit=(EditText)view.findViewById(R.id.account);
        passwordEdit=(EditText)view.findViewById(R.id.password);
        login=(Button)view.findViewById(R.id.login);
        rememberPass=(CheckBox)view.findViewById(R.id.remenber_pass);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        pref= PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isRemember=pref.getBoolean("remember_password",false);
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                if(account.equals("admin")&&password.equals("123456")){
                    editor=pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent();
                    startActivity(intent);
                    getActivity().finish();
                }else{
                    Toast.makeText(getContext(),"account or password is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
