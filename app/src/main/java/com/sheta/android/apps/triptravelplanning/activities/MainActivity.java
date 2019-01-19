package com.sheta.android.apps.triptravelplanning.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sheta.android.apps.triptravelplanning.R;
import com.sheta.android.apps.triptravelplanning.fragments.SignInFragment;
import com.sheta.android.apps.triptravelplanning.fragments.SignUpFragment;

//trip_plan_csed@19
public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        showSingIn();
    }

    public static void showSingIn() {
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                .replace(R.id.frameLayout,signInFragment).commit();
    }

    public static void showSingUp() {
        Fragment signUpFragment = new SignUpFragment();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                .replace(R.id.frameLayout,signUpFragment).commit();
    }
}
