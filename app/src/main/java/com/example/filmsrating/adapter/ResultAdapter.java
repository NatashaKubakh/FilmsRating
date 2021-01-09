package com.example.filmsrating.adapter;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.service.carrier.CarrierMessagingService;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.filmsrating.R;
import com.example.filmsrating.databinding.ResultItemBinding;
import com.example.filmsrating.model.Result;
import com.example.filmsrating.ui.MainActivity;
import com.example.filmsrating.ui.callbacks.MyResultCallback;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class ResultAdapter extends PagedListAdapter<Result, ResultAdapter.ResultViewHolder> {

    private ResultItemBinding binding;
    private MyResultCallback resultCallback;

    public ResultAdapter(MyResultCallback resultCallback) {
        super(Result.CALLBACK);
        this.resultCallback = resultCallback;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.result_item,
                        viewGroup, false);
        binding.setCallback(resultCallback);
        return new ResultViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder resultViewHolder, int i) {
        Result result = getItem(i);
        resultViewHolder.binding.setResult(result);
        if (resultViewHolder.binding.getResult().posterPath != null) {
            String poster = "https://image.tmdb.org/t/p/w220_and_h330_face" + resultViewHolder.binding.getResult().posterPath;
            Glide.with(resultViewHolder.itemView.getContext())
                    .load(poster)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(resultViewHolder.binding.imageFilm);
            if (resultViewHolder.binding.getResult().backdropPath != null) {
                String backGround = "https://image.tmdb.org/t/p/w220_and_h330_face" + resultViewHolder.binding.getResult().backdropPath;
                Glide.with(resultViewHolder.itemView.getContext())
                        .load(backGround)
                        .apply(new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                        )
                        .transform(new BlurTransformation(), new CenterCrop())
                        .into(resultViewHolder.binding.layoutMain);
            }
        }
        resultViewHolder.binding.executePendingBindings();
    }


    public class ResultViewHolder extends RecyclerView.ViewHolder {
        final ResultItemBinding binding;

        public ResultViewHolder(ResultItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
