package ca.csf.pobj.tp3.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import ca.csf.pobj.tp3.cypher.Key;

public class JsonDeserializer {

    private  JsonDeserializer(){
        //static class
    }

    public static Key deserializeKey(String responseBody){

        ObjectMapper mapper = new ObjectMapper();

        Key key = null;
        try {
            key = mapper.readValue(responseBody,Key.class);
        }
        catch (JsonGenerationException e){
            e.printStackTrace(); //BEN_CORRECTION : Exception ignorée. Server error.
        }catch (JsonMappingException e){
            e.printStackTrace(); //BEN_CORRECTION : Exception ignorée. Server error.
        }
        catch (IOException e) {
            e.printStackTrace(); //BEN_CORRECTION : Exception ignorée. Server error.
        }
        //BEN_REVIEW : Lire les warnings. Explications ici : https://stackoverflow.com/a/26266870
        finally {
            return key;
        }
    }
}
