package com.blogspot.techtibet.edxclone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private String[] data;
    public CourseAdapter(String[] data){
        this.data=data;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_course,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    String value=data[position];
    holder.mText.setText(value);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mText;
        View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            mImage=(ImageView)mView.findViewById(R.id.singleimage);
            mText=(TextView)mView.findViewById(R.id.singletext);
        }
    }
}

