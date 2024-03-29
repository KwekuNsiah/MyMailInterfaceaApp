package com.example.mymail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailViewHolder> {

    private List<EmailData> mEmailData;
    private Context mContext;



    public MailAdapter( Context mContext, List<EmailData> mEmailData) {
        this.mEmailData = mEmailData;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_mail_item,
                parent, false);
        return new MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MailViewHolder holder, int position) {
        holder.mIcon.setText(mEmailData.get(position).getmSender().substring(0,1));
        holder.mSender.setText(mEmailData.get(position).getmSender());
        holder.mEmailTitle.setText(mEmailData.get(position).getmTitle());
        holder.mEmailDetails.setText(mEmailData.get(position).getmDetails());
        holder.mEmailTime.setText(mEmailData.get(position).getmTime());
        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256),
                mRandom.nextInt(256));
        ((GradientDrawable) holder.mIcon.getBackground()).setColor(color);

        holder.mFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mFavourite.getColorFilter() != null){
                    holder.mFavourite.clearColorFilter();
                }else {
                    holder.mFavourite.setColorFilter(ContextCompat.getColor(mContext, R.color.colorOrange));
                }
            }
        });

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("sender", holder.mSender.getText().toString());
                mIntent.putExtra("title", holder.mEmailTitle.getText().toString());
                mIntent.putExtra("details", holder.mEmailDetails.getText().toString());
                mIntent.putExtra("time", holder.mEmailTime.getText().toString());
                mIntent.putExtra("icon", holder.mIcon.getText().toString());
                mIntent.putExtra("colorIcon", color);
                mContext.startActivity(mIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mEmailData.size();
    }

    static class MailViewHolder extends RecyclerView.ViewHolder {

        TextView mIcon;
        TextView mSender;
        TextView mEmailTitle;
        TextView mEmailDetails;
        TextView mEmailTime;
        ImageView mFavourite;
        RelativeLayout mLayout;

         MailViewHolder(@NonNull View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.tvIcon);
            mSender = itemView.findViewById(R.id.tvEmailSender);
            mEmailTitle = itemView.findViewById(R.id.tvEmailTitle);
            mEmailDetails = itemView.findViewById(R.id.tvEmailDetails);
            mEmailTime = itemView.findViewById(R.id.tvEmailTime);
            mFavourite = itemView.findViewById(R.id.ivFavorite);
            mLayout = itemView.findViewById(R.id.layout);
        }
    }
}

