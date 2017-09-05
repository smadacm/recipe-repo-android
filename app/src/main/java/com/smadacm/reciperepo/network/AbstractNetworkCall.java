package com.smadacm.reciperepo.network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractNetworkCall {
    protected String url;

    public AbstractNetworkCall(){}

    public void run(){
        new GetRecipesNetworkAsync().execute(this.url);
    }

    public abstract void afterAsync(String result);

    private class GetRecipesNetworkAsync extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... wordsSourceUrl){
            String result = "";
            try {
                URL url = new URL(wordsSourceUrl[0]);
                BufferedReader httpIn = new BufferedReader(new InputStreamReader(url.openStream()));

                StringBuilder buffer = new StringBuilder();
                String line;
                while((line = httpIn.readLine()) != null){
                    buffer.append(line);
                }
                httpIn.close();

                result = buffer.toString();
            } catch(MalformedURLException e){
            } catch (IOException e){
            }

            afterAsync(result);
            return result;
        }
    }
}
