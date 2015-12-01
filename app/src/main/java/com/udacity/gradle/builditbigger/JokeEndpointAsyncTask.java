package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.vadivelansr.myapplication.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by vadivelansr on 12/1/2015.
 */
public class JokeEndpointAsyncTask extends AsyncTask<OnJokeReceivedListener, Void, String> {
    private static JokeApi jokeApi;
    private OnJokeReceivedListener listener;

    @Override
    protected String doInBackground(OnJokeReceivedListener... params) {
        if (jokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });;
            jokeApi = builder.build();
        }
        listener = params[0];
        try {
            return jokeApi.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        listener.onReceive(joke);
    }


}
