package com.example.filmsrating.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.filmsrating.R;
import com.example.filmsrating.adapter.ResultAdapter;
import com.example.filmsrating.databinding.ActivityMainBinding;
import com.example.filmsrating.model.Result;
import com.example.filmsrating.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ResultAdapter resultAdapter;
    private MainActivityViewModel mainActivityViewModel;
    public static final String TAG = "my_tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        Log.d(TAG, "куку");
        resultAdapter = new ResultAdapter();
        binding.filmsList.setLayoutManager(new LinearLayoutManager(this));
        binding.filmsList.setAdapter(resultAdapter);
        binding.setNotEmpty(true);

        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                resultAdapter.submitList(results);

            }
        });
    }
}
