package com.sheta.android.apps.triptravelplanning.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sheta.android.apps.triptravelplanning.model.User;

import java.util.Hashtable;

public class Authentication {

    private boolean state;
    private FirebaseAuth auth;
    private Activity activity;

    public Authentication(Activity activity) {
        auth = FirebaseAuth.getInstance();
        this.activity = activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public boolean signIn(String email, String password) {
        state = false;
        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                        state = true;
                }
            });
        return state;
    }

    public boolean signUp(final Hashtable<String, String> userData) {
        state = false;
        //create user
        String email = userData.get(Constants.EMAIL);
        String password = userData.get(Constants.PASSWORD);
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        state = register(userData);
                    }
                }
            });
        return state;
    }

    public boolean register(Hashtable<String, String> userData) {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference users = database.getReference("USERS");

            // Creating new user node, which returns the unique key value
            // new user node would be /users/$userid/
            String userId = users.push().getKey();

            // creating user object
            User user = new User();
            user.setId(userId);
            user.setUserName(userData.get(Constants.USERNAME));
            user.setEmail(userData.get(Constants.EMAIL));
            user.setPhoneNumber(userData.get(Constants.CONTACT_NUMBER));
            user.setPassword(userData.get(Constants.PASSWORD));
            //set profile image uri
            user.setProfileImageUri("");
            // pushing user to 'users' node using the userId
            users.child(userId).setValue(user);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public void resetPassword(String email) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                    } else {

                    }
                }
            });
    }

    public boolean signOut(String email , String password) {
        return false;
    }

}
