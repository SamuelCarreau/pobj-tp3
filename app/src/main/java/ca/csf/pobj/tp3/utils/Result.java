package ca.csf.pobj.tp3.utils;

import ca.csf.pobj.tp3.cypher.Key;

public class Result {

    private Key key;

    private boolean connectivityError;

    private boolean serverError;

    private Result(Key key, boolean connectivityError, boolean serverError) {
        this.key = key;
        this.connectivityError = connectivityError;
        this.serverError = serverError;
    }

    public Key getKey() {
        return key;
    }

    public boolean isConnectivityError() {
        return connectivityError;
    }

    public boolean isServerError() {
        return serverError;
    }

    public static Result Ok(Key key){
        return new Result(key,false,false);
    }

    public static Result connectivityError(){
        return new Result(null,true,false);
    }

    public static Result serverError(){
        return new Result(null,false,true);
    }
}
