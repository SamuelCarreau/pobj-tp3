package ca.csf.pobj.tp3.cypher;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Key {

    private int id;
    private String outputCharacters;
    private String inputCharacters;


    @JsonCreator
    public Key(int id, String outputCharacters,String inputCharacters) {
        this.id = id;
        this.outputCharacters = outputCharacters;
        this.inputCharacters = inputCharacters;
    }

    public int getId() {
        return id;
    }

    public String encrypt(String cleanText){
        String encryptText = "";
        String substitutionString ="";

        // TODO : receive clean text and return encrypted text

        return encryptText;
    }

    public String decrypt(String encryptText){
        String decryptText = "";
        String substitutionString ="";

        // TODO : receive encrypted text and return decrypted text

        return decryptText;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", outputCharacters='" + outputCharacters + '\'' +
                ", inputCharacters='" + inputCharacters + '\'' +
                '}';
    }
}
