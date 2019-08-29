package com.example.vplay.network.interfaces;

import androidx.annotation.NonNull;

import com.example.vplay.network.models.NetworkResponse;

public interface OnNetworkQueryCompletionListener {
    void onNetworkQueryCompleted(@NonNull NetworkResponse networkResponse);
}
