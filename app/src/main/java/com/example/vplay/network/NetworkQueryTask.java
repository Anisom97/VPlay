package com.example.vplay.network;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vplay.Constants;
import com.example.vplay.network.interfaces.OnNetworkQueryCompletionListener;
import com.example.vplay.network.models.NetworkResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkQueryTask extends AsyncTask<String, Void, List<String>> {
    private @NonNull
    String url, method;

    private @Nullable
    OnNetworkQueryCompletionListener onNetworkQueryCompletionListener;

    private @Nullable
    List<NameValuePair> entity;

    public NetworkQueryTask(@NonNull String url,
                            @NonNull String method) {
        this.url = url;
        this.method = method;
    }

    public NetworkQueryTask(@NonNull String url,
                            @NonNull String method,
                            @Nullable OnNetworkQueryCompletionListener onNetworkQueryCompletionListener) {
        this.url = url;
        this.method = method;
        this.onNetworkQueryCompletionListener = onNetworkQueryCompletionListener;
    }

    public NetworkQueryTask(@NonNull String url,
                            @NonNull String method,
                            @Nullable List<NameValuePair> entity) {
        this.url = url;
        this.method = method;
        this.entity = entity;
    }

    public NetworkQueryTask(@NonNull String url,
                            @NonNull String method,
                            @Nullable OnNetworkQueryCompletionListener onNetworkQueryCompletionListener,
                            @Nullable List<NameValuePair> entity) {
        this.url = url;
        this.method = method;
        this.onNetworkQueryCompletionListener = onNetworkQueryCompletionListener;
        this.entity = entity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<String> s) {
        super.onPostExecute(s);

        if (onNetworkQueryCompletionListener != null) {
            NetworkResponse networkResponse;

            if (s == null)
                networkResponse = new NetworkResponse(NetworkResponse.NETWORK_ERROR);

            else if (Constants.SERVER_QUERY_ERROR.equals(s.get(s.size() - 1)))
                networkResponse = new NetworkResponse(NetworkResponse.QUERY_ERROR, s.subList(0, s.size() - 1));

            else networkResponse = new NetworkResponse();

            onNetworkQueryCompletionListener.onNetworkQueryCompleted(networkResponse);
        }
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            if (entity != null) httpPost.setEntity(new UrlEncodedFormEntity(entity));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            Scanner sc = new Scanner(httpEntity.getContent());

            List<String> response = new ArrayList<>();

            while (sc.hasNext()) response.add(sc.next());

            return response;
        }
        catch (ClientProtocolException ignored) {}
        catch (IOException ignored) {}

        return null;
    }
}
