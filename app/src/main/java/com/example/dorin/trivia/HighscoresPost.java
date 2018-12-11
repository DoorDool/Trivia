package com.example.dorin.trivia;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;

public class HighscoresPost implements Response.Listener<String>, Response.ErrorListener{
    Context context;
    Callback callback_activity;
    String points;
    String name;
    public class PostRequest extends StringRequest {

        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }



        @Override
        protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("points", points);
            params.put("name", name);
            return params;
        }
    }

    public interface Callback {
        void gotpostHighscores(String highscores);
        void gotpostHighscoresError(String message);
    }

    public HighscoresPost(Context context) {
        this.context = context;
    }

    public void postHighscores(Context aContext, String score_points, String score_name){
        this.context = aContext;
        points = score_points;
        name = score_name;
        String json_url = "https://ide50-doordool.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);

        PostRequest post = new PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(post);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotpostHighscoresError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotpostHighscores(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

    }



}

