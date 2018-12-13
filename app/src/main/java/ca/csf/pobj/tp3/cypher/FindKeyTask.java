package ca.csf.pobj.tp3.cypher;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.csf.pobj.tp3.utils.Result;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FindKeyTask extends AsyncTask<Integer,Void,Result> {


    public static final String URL = "https://m1t2.csfpwmjv.tk/api/key/%d";

    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    @Override
    protected Result doInBackground(Integer... keys) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(URL,keys[0]))
                .build();
        Call call = client.newCall(request);

        try{
            Response response = call.execute();
            if(response.isSuccessful()){
                String responseBody = response.body().string();

                ObjectMapper mapper = new ObjectMapper();
                Key key = mapper.readValue(responseBody,Key.class);
                return Result.Ok(key);
            }
            else {
                return Result.serverError();
            }

        }catch (IOException exception){
            exception.printStackTrace();
            return  Result.connectivityError();
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        for(Listener listener : listeners){
            listener.onKeyFound(result);
        }
    }

    public interface Listener {
        void onKeyFound(Result key);
    }
}
