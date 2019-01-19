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

public class SignInFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUpBtn;
    private Button signInBtn;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        emailEditText = (EditText) view.findViewById(R.id.emailLoginEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordLoginEditText);

        signInBtn = (Button) view.findViewById(R.id.signInButton);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInBtn.setEnabled(false);
                signIn();
                signInBtn.setEnabled(true);
            }
        });

        signUpBtn = (Button) view.findViewById(R.id.moveToSignUpButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showSingUp();
            }
        });

        return view;
    }

    private void signIn() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(checkValidation(email, password)) {
            Authentication authentication = new Authentication(getActivity());
            if (authentication.signIn(email, password)) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
                getActivity().finish();
            } else {
                Toast.makeText(getContext(), "sign in failed", Toast.LENGTH_SHORT).show();
            }
        }

        Toast.makeText(getActivity(),email,Toast.LENGTH_LONG).show();
    }

    private boolean checkValidation(String email, String password) {
        return false;
    }
}
