package com.example.studentportal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PortalObjectAdapter extends RecyclerView.Adapter<PortalObjectViewHolder> {

    public List<PortalObject> listPortalObject;

    public PortalObjectAdapter(List<PortalObject> listPortalObject) {
        this.listPortalObject = listPortalObject;
    }


    @NonNull
    @Override
    public PortalObjectViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell, parent, false);
        return new PortalObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PortalObjectViewHolder holder, int position) {
        final PortalObject portalObject = listPortalObject.get(position);
        holder.portalTitle.setText(portalObject.getTitle());
    }

    @Override
    public int getItemCount() {
        return listPortalObject.size();
    }
}
