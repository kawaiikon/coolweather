package com.coolweather.coolweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coolweather.coolweather.R;
import com.coolweather.coolweather.adapter.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bian on 2016/5/5 11:32.
 */
public class WeatherFragment extends Fragment {

    private RecyclerView recyclerView;
    private String city;

    public static WeatherFragment newInstance(String city) {
        Bundle args = new Bundle();
        args.putString("city", city);
        WeatherFragment pageFragment = new WeatherFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        city = getArguments().getString("city");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        List<String> list = new ArrayList<>();
        for (int i = 0;i<12;i++){
            list.add("" + i);
        }
        recyclerView.setAdapter(new WeatherAdapter(view.getContext(), list));
        return view;
    }
}
