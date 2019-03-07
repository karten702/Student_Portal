package com.example.studentportal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PortalObjectViewHolder extends RecyclerView.ViewHolder {

    public TextView portalTitle;
    public View view;

    public PortalObjectViewHolder(@NonNull View itemView) {
        super(itemView);
        portalTitle = itemView.findViewById(R.id.portal_item);
        view = itemView;
    }
}
