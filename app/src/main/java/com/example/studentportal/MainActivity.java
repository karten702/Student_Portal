package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private List<PortalObject> portals;
    private PortalObjectAdapter portalAdapter;
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;

    public static final String ADD_PORTAL = "NewPortal";
    public static final int NewPortalCode = 4321;

    public static final String VIEW_PORTAL = "ViewPortal";
    public static final int ViewPortalCode = 3421;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.portalRecycler);
        portals = new ArrayList<>();

        RecyclerView.LayoutManager layout = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setHasFixedSize(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddPortal.class);
            startActivityForResult(intent, NewPortalCode);
            }
        });

        recyclerView.addOnItemTouchListener(this);
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI(){
        if (portalAdapter == null){
            portalAdapter = new PortalObjectAdapter(portals);
            recyclerView.setAdapter(portalAdapter);
        }
        else
            portalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NewPortalCode){
            if(resultCode == RESULT_OK){
                PortalObject newPortal = data.getParcelableExtra(MainActivity.ADD_PORTAL);
                portals.add(newPortal);
                updateUI();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());

        if(child != null) {
            int position = rv.getChildAdapterPosition(child);

            if (gestureDetector.onTouchEvent(e)) {
                Intent intent = new Intent(MainActivity.this, web_viewer.class);
                intent.putExtra(VIEW_PORTAL, portals.get(position));
                startActivityForResult(intent, ViewPortalCode);
            }
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
