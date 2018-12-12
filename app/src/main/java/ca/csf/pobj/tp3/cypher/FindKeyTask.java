package ca.csf.pobj.tp3.cypher;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.csf.pobj.tp3.utils.JsonDeserializer;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FindKeyTask extends AsyncTask<Integer,Void,Key> {


    public static final String URL = "https://m1t2.csfpwmjv.tk/api/key/%d";

    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    @Override
    protected Key doInBackground(Integer... keys) {

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
                //TODO : create the Key object and return it
                Key key = mapper.readValue(responseBody,Key.class);
                System.out.println(key);
            }
            else {
                // TODO : show error code server error.
            }

        }catch (IOException exception){
            exception.printStackTrace();
            //TODO : show error code Connectivity Error.
        }

        return null;
    }

    public interface Listener {
        void onKeyFound(Key key);

    }
}
