package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddPortal extends AppCompatActivity {

    private TextView urlTextView;
    private TextView titleTextView;
    private Button newPortalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        urlTextView = findViewById(R.id.urlValue);
        titleTextView = findViewById(R.id.portalTitleText);
        newPortalButton = findViewById(R.id.addPortalButton);

        newPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = titleTextView.getText().toString();
                String urlText = urlTextView.getText().toString();

                if(!TextUtils.isEmpty(titleText) && !TextUtils.isEmpty(urlText) && !TextUtils.equals(urlText, getString(R.string.UrlPrefixText))){
                    PortalObject portalToAdd = new PortalObject(titleText, urlText);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.ADD_PORTAL, portalToAdd);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else
                    Snackbar.make(view, "There is no URL or the title is empty", Snackbar.LENGTH_LONG);
            }
        });
    }
}
