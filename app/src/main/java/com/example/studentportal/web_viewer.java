package com.example.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class web_viewer extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewer);

        webView = findViewById(R.id.portalWebViewer);
        final PortalObject portal = getIntent().getParcelableExtra(MainActivity.VIEW_PORTAL);
        webView.loadUrl(portal.getUrl());
    }
}
