package com.example.filmsrating.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.filmsrating.R;
import com.example.filmsrating.databinding.ActivitySecondBinding;
import com.example.filmsrating.model.Result;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            Result result = (Result) b.getSerializable(MainActivity.KEY_RESULT_OBJECT);
            binding.setResult(result);
            if (binding.getResult().backdropPath != null) {
                String backGround = "https://image.tmdb.org/t/p/w220_and_h330_face" + result.backdropPath;
                Glide.with(this)
                        .load(backGround)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(new ColorDrawable(Color.TRANSPARENT))
                        .transform(new BlurTransformation(4, 2))
                        .into(binding.layoutMain);
            }
        }
    }
}