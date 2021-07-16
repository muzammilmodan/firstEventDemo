package brkckr.first.eleven;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeepLinkingActivity extends AppCompatActivity {


    MultiAutoCompleteTextView inputEditText;

    String[] COUNTRIES = new String[]{"Belgium","Behrain", "India West","India East","France", "Italy", "Germany", "Spain","Beloader","India","Ireland"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_linking);

        inputEditText = findViewById(R.id.edtText);
        Intent intent = getIntent();
        Uri data = intent.getData();
        Log.e("----=====> ", "" + data);
        adapter = new ArrayAdapter<String>(DeepLinkingActivity.this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        inputEditText.setAdapter(adapter);
        inputEditText.setThreshold(1); //Set number of characters before the dropdown should be shown

        changedTokenizer();
        changedListners();

        setDeepLinkingdata();

    }

    private void setDeepLinkingdata() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                    }
                });
    }


    private void changedTokenizer() {

        inputEditText.setTokenizer(new MultiAutoCompleteTextView.Tokenizer() {

            @Override
            public CharSequence terminateToken(CharSequence text) {
                int i = text.length();

                while (i > 0 && text.charAt(i - 1) == ' ') {
                    i--;
                }

                if (i > 0 && text.charAt(i - 1) == ' ') {
                    Log.e("IF terminateToken","===---=> "+text);
                    return text;
                } else {
                    if (text instanceof Spanned) {
                        Log.e("ELSE IF terminateToken","===---=> "+text);
                        SpannableString sp = new SpannableString(text + " ");
                        TextUtils.copySpansFrom((Spanned) text, 0, text.length(), Object.class, sp, 0);
                        return sp;
                    } else {
                        Log.e("ELSE terminateToken","===---=> "+text);

                        text = "<font color='#EE0000'>"+text+"</font>";

                        return Html.fromHtml(text+"") + " ";
                    }
                }
            }

            @Override
            public int findTokenStart(CharSequence text, int cursor) {
                int i = cursor;
                Log.e("findTokenStart","===---=> "+text);
                while (i > 0 && text.charAt(i - 1) != '@') {
                    i--;
                }

                //Check if token really started with @, else we don't have a valid token
                if (i < 1 || text.charAt(i - 1) != '@') {
                    return cursor;
                }

                return i;
            }

            @Override
            public int findTokenEnd(CharSequence text, int cursor) {
                int i = cursor;
                int len = text.length();
                Log.e("findTokenEnd","===---=> "+text);
                while (i < len) {
                    if (text.charAt(i) == ' ') {
                        return i;
                    } else {
                        i++;
                    }
                }

                return len;
            }
        });
    }

    private void changedListners() {
        inputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Layout layout = inputEditText.getLayout();
                int pos = inputEditText.getSelectionStart();
                int line = layout.getLineForOffset(pos);
                int baseline = layout.getLineBaseline(line);

                int bottom = inputEditText.getHeight();

                inputEditText.setDropDownVerticalOffset(baseline - bottom);


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }




}