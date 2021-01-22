package com.example.filmsrating.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.filmsrating.R;
import com.example.filmsrating.adapter.ResultAdapter;
import com.example.filmsrating.databinding.ActivityMainBinding;
import com.example.filmsrating.model.Result;
import com.example.filmsrating.ui.callbacks.MyResultCallback;
import com.example.filmsrating.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ResultAdapter resultAdapter;
    private MainActivityViewModel mainActivityViewModel;
    public static final String TAG = "my_tag";
    public static final String KEY_RESULT_OBJECT = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        resultAdapter = new ResultAdapter(myResultCallBack);
        binding.filmsList.setLayoutManager(new LinearLayoutManager(this));
        binding.setNotEmpty(true);
        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                resultAdapter.submitList(results);
            }
        });
        binding.filmsList.setAdapter(resultAdapter);
    }

    private final MyResultCallback myResultCallBack = result -> {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(KEY_RESULT_OBJECT, result);
        startActivity(intent);
    };

}
