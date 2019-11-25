package com.blogspot.sslaia.achidanews.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blogspot.sslaia.achidanews.R;
import com.blogspot.sslaia.achidanews.adapter.NewsAdapter;
import com.blogspot.sslaia.achidanews.databinding.NewsActivityBinding;
import com.blogspot.sslaia.achidanews.helpers.ConnectionLiveData;
import com.blogspot.sslaia.achidanews.helpers.ConnectionModel;
import com.blogspot.sslaia.achidanews.helpers.Controller;
import com.blogspot.sslaia.achidanews.viewmodel.NewsViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class GuardianFragment extends Fragment {

    private NewsActivityBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CollapsingToolbarLayout collapsingToolbarLayout = getActivity().findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.menu_guardian));
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // By putting the databinding inflater here
        // the issues with toolbar/drawer disappearance is solved
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PreferenceManager.setDefaultValues(getContext(), R.xml.settings, false);
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        // Check preferences whether to show images in the news list
        boolean showImages = mPrefs.getBoolean("showImages", true);

        String QUERY = null;
        String SECTION = null;
        String ORDER_BY = getString(R.string.order_by_newest);
        String SHOW_FIELDS = getString(R.string.show_fields);
        String API_KEY = getString(R.string.api_key1);

        NewsViewModel model = new NewsViewModel(Controller.create(getActivity()), QUERY, SECTION, ORDER_BY, SHOW_FIELDS, API_KEY);
        NewsAdapter adapter = new NewsAdapter(getContext(), showImages);
        model.getNewsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);
        binding.recyclerView.setAdapter(adapter);

        // Thank for Saurabh(aqua) for this simple connectivity solution solution
        // https://gist.github.com/aqua30/e16509f70176b6770a3373aa08cf29a3
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getActivity());
        connectionLiveData.observe(getViewLifecycleOwner(), new Observer<ConnectionModel>() {
            @Override
            public void onChanged(ConnectionModel connection) {
                if (!connection.getIsConnected()) {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.headlines_to_connection);
                }
            }
        });
    }
}
