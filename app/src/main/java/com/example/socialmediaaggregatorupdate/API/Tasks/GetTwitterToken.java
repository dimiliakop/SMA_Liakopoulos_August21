package com.example.socialmediaaggregatorupdate.API.Tasks;

import android.os.AsyncTask;

import androidx.core.util.Consumer;

import com.example.socialmediaaggregatorupdate.API.Wrappers.TwitterWrapper;
import twitter4j.TwitterException;
import twitter4j.auth.OAuth2Token;

public class GetTwitterToken extends AsyncTask<Void, Void, OAuth2Token> {

    private final Consumer<Void> callback;

    public GetTwitterToken(Consumer<Void> callback) {
        this.callback = callback;
    }

    @Override
    protected OAuth2Token doInBackground(Void... voids) {
        OAuth2Token token = null;
        try {
            token = TwitterWrapper.getInstance().requestOAuth2Token();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    protected void onPostExecute(OAuth2Token token) {
        TwitterWrapper.getInstance().setOAuth2Token(token);
        callback.accept(null);
    }
}
