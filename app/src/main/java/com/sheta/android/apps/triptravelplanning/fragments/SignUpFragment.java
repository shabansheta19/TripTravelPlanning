package com.sheta.android.apps.triptravelplanning.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sheta.android.apps.triptravelplanning.R;
import com.sheta.android.apps.triptravelplanning.activities.MainActivity;
import com.sheta.android.apps.triptravelplanning.activities.ProfileActivity;
import com.sheta.android.apps.triptravelplanning.utils.Authentication;
import com.sheta.android.apps.triptravelplanning.utils.Constants;

import java.util.Hashtable;

public class SignUpFragment extends Fragment {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText contactNumEditText;
    private EditText passwordEditText;
    private EditText confirmPassEditText;
    private Button signUpBtn;
    private Button signInBtn;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        contactNumEditText = (EditText) view.findViewById(R.id.contactNumEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        confirmPassEditText = (EditText) view.findViewById(R.id.confirmPasswordEditText);

        signUpBtn = (Button) view.findViewById(R.id.signUpBttton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInBtn.setEnabled(false);
                signUp();
                signInBtn.setEnabled(true);
            }
        });

        signInBtn = (Button) view.findViewById(R.id.moveToLoginButton);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showSingIn();
            }
        });

        return view;
    }


    private void signUp() {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String contactNum = contactNumEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPassEditText.getText().toString();

        Hashtable<String, String> userData = new Hashtable<>();
        userData.put(Constants.USERNAME,username);
        userData.put(Constants.EMAIL,email);
        userData.put(Constants.CONTACT_NUMBER,contactNum);
        userData.put(Constants.PASSWORD,password);

        if(validate(userData, confirmPassword)) {
            Authentication authentication = new Authentication(getActivity());
            if (authentication.signUp(userData)) {
                //go to profile page
                startActivity(new Intent(getActivity(), ProfileActivity.class));
                getActivity().finish();
            } else {
                Toast.makeText(getContext(), "sign up failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validate(Hashtable<String, String> uaerData, String confirmPassword) {
        return true;
    }
}
