package ca.csf.pobj.tp3.cypher;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class FindKeyTask extends AsyncTask<Integer,Void,String> {


    public static final String URL = "";

    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        return null;
    }

    public interface Listener {
        void onKeyFound(Key key);

    }
}
