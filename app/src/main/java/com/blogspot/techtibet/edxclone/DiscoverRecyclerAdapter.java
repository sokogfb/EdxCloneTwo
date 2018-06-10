package com.blogspot.techtibet.edxclone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

class DiscoverRecyclerAdapter extends RecyclerView.Adapter<DiscoverRecyclerAdapter.ViewHolder> {
    List<Discover> discoverList;
    Context context;
    DiscoverRecyclerAdapter(List<Discover> discoverList,Context context){
        this.discoverList=discoverList;
        this.context=context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_discover_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title=discoverList.get(position).getTitle();
        String imageurl=discoverList.get(position).getImageurl();
        String date=discoverList.get(position).getTime();
        String company=discoverList.get(position).getCompany();
        holder.mCompany.setText(company);
        holder.mDate.setText(date);
        holder.setImage(imageurl);
        holder.mTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return discoverList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        View mView;
        ImageView mCourseImg;
        TextView mDate;
        TextView mCompany;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            mTitle=mView.findViewById(R.id.dis_title);
            mCourseImg=mView.findViewById(R.id.dis_img);
            mDate=mView.findViewById(R.id.dis_time);
            mCompany=mView.findViewById(R.id.dis_company);


        }

        public void setImage(String imageurl) {
            Glide.with(context).load(imageurl).into(mCourseImg);
        }
    }
}
