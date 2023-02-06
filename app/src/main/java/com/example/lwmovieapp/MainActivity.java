package com.example.lwmovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.lwmovieapp.adapter.MovieListAdapter;
import com.example.lwmovieapp.databinding.ActivityMainBinding;
import com.example.lwmovieapp.model.MovieModel;
import com.example.lwmovieapp.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;

    private ActivityMainBinding binding;

    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        MovieListAdapter adapter = new MovieListAdapter(this, movieModelList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null) {
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModels);
                    binding.noResultTv.setVisibility(View.GONE);
                } else {
                    binding.noResultTv.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }
}