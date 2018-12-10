package ca.csf.pobj.tp3.cypher;

public class Key {
    public final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .";

    private int keyNumber;
    private String substitutionString;

    public Key(int keyNumber, String substitutionString) {
        this.keyNumber = keyNumber;
        this.substitutionString = substitutionString;
    }

    public int getKeyNumber() {
        return keyNumber;
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
}
