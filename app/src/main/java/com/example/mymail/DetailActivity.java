package com.example.mymail;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView mIcon;
    TextView mSender;
    TextView mEmailTitle;
    TextView mEmailDetails;
    TextView mEmailTime;
    ImageView mFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);

        mIcon = findViewById(R.id.tvIcon);
        mSender = findViewById(R.id.tvEmailSender);
        mEmailTitle = findViewById(R.id.tvEmailTitle);
        mEmailDetails = findViewById(R.id.tvEmailDetails);
        mEmailTime = findViewById(R.id.tvEmailTime);
        mFavourite = findViewById(R.id.ivFavorite);

    Bundle mBundle = getIntent().getExtras();
    if (mBundle != null){
        mIcon.setText(mBundle.getString("icon"));
        ((GradientDrawable) mIcon.getBackground()).setColor(mBundle.getInt("colorIcon"));
        mSender.setText(mBundle.getString("sender"));
        mEmailTitle.setText(mBundle.getString("title"));
        mEmailDetails.setText(mBundle.getString("details"));
        mEmailTime.setText(mBundle.getString("time"));
    }

    mFavourite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mFavourite.getColorFilter() != null){
                mFavourite.clearColorFilter();
            }else {
                mFavourite.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                        R.color.colorOrange));
            }
        }
    });

    }
}
