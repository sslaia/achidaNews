package com.blogspot.sslaia.achidanews.ui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.blogspot.sslaia.achidanews.R;
import com.blogspot.sslaia.achidanews.helpers.ConnectionLiveData;
import com.blogspot.sslaia.achidanews.helpers.ConnectionModel;

public class ConnectionFragment extends Fragment {

    private Activity activity;
    private Context context;

    public ConnectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_connection, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(activity);
        connectionLiveData.observe(getViewLifecycleOwner(), new Observer<ConnectionModel>() {
            @Override
            public void onChanged(ConnectionModel connection) {
                if (connection.getIsConnected()) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                    navController.navigate(R.id.connection_to_headlines);
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }
}
