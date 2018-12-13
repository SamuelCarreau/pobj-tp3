package ca.csf.pobj.tp3.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import ca.csf.pobj.tp3.R;
import ca.csf.pobj.tp3.utils.Result;
import ca.csf.pobj.tp3.utils.view.CharactersFilter;
import ca.csf.pobj.tp3.utils.view.KeyPickerDialog;
import ca.csf.pobj.tp3.cypher.*;

import static ca.csf.pobj.tp3.utils.RandomUtil.RandomRange;

public class MainActivity extends AppCompatActivity implements FindKeyTask.Listener{

    private static final int KEY_LENGTH = 5;
    private static final int MAX_KEY_VALUE = (int) Math.pow(10, KEY_LENGTH) - 1;
    private static final int MIN_KEY_VALUE = 0;
    private static final String CURRENT_KEY = "currentKey";
    public static final String INPUT_EDIT_TEXT = "INPUT_EDIT_TEXT";
    public static final String OUTPUT_TEXT_VIEW = "outputTextView";

    private View rootView;
    private EditText inputEditText;
    private TextView outputTextView;
    private TextView currentKeyTextView;
    private ProgressBar progressBar;

    private Key currentKey;
    private int currentKeyNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.rootView);
        progressBar = findViewById(R.id.progressbar);
        inputEditText = findViewById(R.id.input_edittext);
        inputEditText.setFilters(new InputFilter[]{new CharactersFilter()});
        outputTextView = findViewById(R.id.output_textview);
        currentKeyTextView = findViewById(R.id.current_key_textview);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentKey == null) {
            currentKeyNumber = RandomRange(MIN_KEY_VALUE, MAX_KEY_VALUE);
            createCurrentKey(currentKeyNumber);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_KEY, currentKey);
        outState.putString(INPUT_EDIT_TEXT, String.valueOf(inputEditText.getText()));
        outState.putString(OUTPUT_TEXT_VIEW, String.valueOf(outputTextView.getText()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentKey = savedInstanceState.getParcelable(CURRENT_KEY);
        currentKeyNumber = currentKey.getId();
        inputEditText.setText(savedInstanceState.getString(INPUT_EDIT_TEXT));
        outputTextView.setText(savedInstanceState.getString(OUTPUT_TEXT_VIEW));
    }

    private void createCurrentKey(int keyValue) {
        //TODO: showLoadingBAR

        currentKeyTextView.setText(String.format(getResources().getString(R.string.text_current_key), keyValue));

        FindKeyTask task = new FindKeyTask();
        task.addListener(this);
        task.execute(keyValue);

    }

    private void setCurrentKey(Key key){
        currentKey = key;
    }

    private void showKeyPickerDialog(int key) {
        KeyPickerDialog.make(this, KEY_LENGTH)
                .setKey(key)
                .setConfirmAction(this::fetchSubstitutionCypherKey)
                .show();
    }

    private void showCopiedToClipboardMessage() {
        Snackbar.make(rootView, R.string.text_copied_output, Snackbar.LENGTH_SHORT).show();
    }

    private void showConnectionError() {
        Snackbar.make(rootView, R.string.text_connectivity_error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.text_activate_wifi, (view) -> showWifiSettings())
                .show();
    }

    private void showServerError() {
        Snackbar.make(rootView, R.string.text_server_error, Snackbar.LENGTH_INDEFINITE)
                .show();
    }

    private void showWifiSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }

    private void fetchSubstitutionCypherKey(int key) {
        createCurrentKey(key);
    }

    @SuppressWarnings("ConstantConditions")
    private void putTextInClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText(getResources().getString(R.string.clipboard_encrypted_text), text));
    }

    public void onEncryptButtonClicked(View view) {
        if (currentKey != null){
            outputTextView.setText(currentKey.encrypt(String.valueOf(inputEditText.getText())));
        }
        else{
            createCurrentKey(currentKeyNumber);
        }
        //TODO : Encrypt the text in the inputEditText when clicked and send result to outputTextView
    }

    public void onDecryptButtonClicked(View view) {
        if (currentKey != null){
            outputTextView.setText(currentKey.decrypt(String.valueOf(inputEditText.getText())));
        }
        else{
            createCurrentKey(currentKeyNumber);
        }
        //TODO : Decrypt the text in the inputEditText when clicked and send result to outputTextView
    }

    public void onKeySelectButtonClicked(View view) {
        showKeyPickerDialog(currentKey.getId());
    }

    public void onCopyButtonClicked(View view) {
        putTextInClipboard(String.valueOf(outputTextView.getText()));
        showCopiedToClipboardMessage();
    }


    @Override
    public void onKeyFound(Result result) {
        //TODO : stop loading view
        if(result.isConnectivityError()){
            showConnectionError();
        }
        else if(result.isServerError()){
            showServerError();
        }
        else {
            setCurrentKey(result.getKey());
        }
    }
}
