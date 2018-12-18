package ca.csf.pobj.tp3.cypher;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Key implements Parcelable {

    private int id;
    private String outputCharacters;
    private String inputCharacters;


    @JsonCreator
    public Key(@JsonProperty("id") int id,
               @JsonProperty("outputCharacters") String outputCharacters,
               @JsonProperty("inputCharacters") String inputCharacters) {
        this.id = id;
        this.outputCharacters = outputCharacters;
        this.inputCharacters = inputCharacters;
    }





    public int getId() {
        return id;
    }

    public String encrypt(String cleanText){
        String encryptText = "toto";

        // TODO : receive clean text and return encrypted text

        return encryptText;
    }

    public String decrypt(String encryptText){
        String decryptText = "tata";

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

    // region Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(inputCharacters);
        dest.writeString(outputCharacters);
    }

    private Key(Parcel in){
        this.id = in.readInt();
        this.inputCharacters = in.readString();
        this.outputCharacters = in.readString();
    }

    public static final Parcelable.Creator<Key> CREATOR = new Creator<Key>() {
        @Override
        public Key createFromParcel(Parcel source) {
            return new Key(source);
        }

        @Override
        public Key[] newArray(int size) {
            return new Key[size];
        }
    };

    // endregion
}
