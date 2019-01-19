package com.sheta.android.apps.triptravelplanning.activities;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sheta.android.apps.triptravelplanning.R;
import com.sheta.android.apps.triptravelplanning.model.Trip;

public class NewTripActivity extends AppCompatActivity {

    private EditText tripNameEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private EditText locationEditText;
    private EditText inviteFriendEditText;
    private ImageButton inviteFriendButton;
    private Button submitBtn;
    private Location tripLocation;
    private Trip newTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        newTrip = new Trip();

        initialUI();
        addClickListener();
    }

    private void addClickListener() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTrip.setTripName(tripNameEditText.getText().toString());
                newTrip.setStartDate(startDateEditText.getText().toString());
                newTrip.setEndDate(endDateEditText.getText().toString());
                newTrip.setLocation(locationEditText.getText().toString());
                newTrip.setLocationLat(tripLocation.getLatitude());
                newTrip.setLocationLng(tripLocation.getLongitude());
            }
        });

        inviteFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTrip.addUser(inviteFriendEditText.getText().toString());
            }
        });
    }

    private void initialUI() {
        tripNameEditText = (EditText) findViewById(R.id.trip_name_editTxt);
        startDateEditText = (EditText) findViewById(R.id.trip_start_date_editTxt);
        endDateEditText = (EditText) findViewById(R.id.trip_end_date_editTxt);
        locationEditText = (EditText) findViewById(R.id.trip_location_editTxt);
        inviteFriendEditText = (EditText) findViewById(R.id.trip_invite_friend_editTxt);
        inviteFriendButton = (ImageButton) findViewById(R.id.invite_friend_button);
        submitBtn = (Button) findViewById(R.id.submit_new_trip_button);
    }
}
