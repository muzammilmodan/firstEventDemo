package brkckr.first.eleven;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appinvite.FirebaseAppInvite;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;



public class DepplinkingDataActivity extends AppCompatActivity {

    String dynamicComUrl="https://www.hoofapp.com/"; //https://www.example.com/
    String createDynamicURl = "https://hoof.page.link/6eZ4"; //https://example.page.link


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depplinking_data);

        Intent intent = getIntent();

        try {
            if (intent != null) {
                //intent.getStringExtra()
                handleDeepLink(intent);
                //getInvitation(intent);

                Uri data = intent.getData();
                //data.getQueryParameter("key");
                Log.e("data========--=>",""+data);
                String path = data.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                Log.e("code data========--=>",""+idStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        createDynamicLink_Basic();
        createDynamicLink_Advanced();
        createShortLink();
        shortenLongLink();
        //buildShortSuffix();
    }



    public void createDynamicLink_Basic() {
        // [START create_link_basic]
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(dynamicComUrl))
                .setDomainUriPrefix(createDynamicURl)
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                .buildDynamicLink();

        Uri dynamicLinkUri = dynamicLink.getUri();
        if(dynamicLinkUri !=null){
            Log.e("Get Url data========--=>",""+dynamicLinkUri);
        }
        // [END create_link_basic]
    }

    public void createDynamicLink_Advanced() {
        // [START create_link_advanced]
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(dynamicComUrl))
                .setDomainUriPrefix(createDynamicURl)
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder("com.example.android")
                                .setMinimumVersion(125)
                                .build())
                .setIosParameters(
                        new DynamicLink.IosParameters.Builder("com.example.ios")
                                .setAppStoreId("123456789")
                                .setMinimumVersion("1.0.1")
                                .build())
                .setGoogleAnalyticsParameters(
                        new DynamicLink.GoogleAnalyticsParameters.Builder()
                                .setSource("orkut")
                                .setMedium("social")
                                .setCampaign("example-promo")
                                .build())
                .setItunesConnectAnalyticsParameters(
                        new DynamicLink.ItunesConnectAnalyticsParameters.Builder()
                                .setProviderToken("123456")
                                .setCampaignToken("example-promo")
                                .build())
                .setSocialMetaTagParameters(
                        new DynamicLink.SocialMetaTagParameters.Builder()
                                .setTitle("Example of a Dynamic Link")
                                .setDescription("This link works whether the app is installed or not!")
                                .build())
                .buildDynamicLink();  // Or buildShortDynamicLink()
        // [END create_link_advanced]
    }

    public void createShortLink() {
        // [START create_short_link]
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(dynamicComUrl))
                .setDomainUriPrefix(createDynamicURl)
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();

                            Log.e("shortLink========--=>",""+shortLink);
                            Log.e("flowchartLink========--=>",""+flowchartLink);
                        } else {
                            // Error
                            // ...
                        }
                    }
                });
        // [END create_short_link]
    }

    public void shortenLongLink() {
        // [START shorten_long_link]
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(Uri.parse("https://example.page.link/?link=https://www.example.com/&apn=com.example.android&ibn=com.example.ios"))
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                        } else {
                            // Error
                            // ...
                        }
                    }
                });
        // [END shorten_long_link]
    }


    public void  shareDataClick(View view){
        shareLink(Uri.parse(createDynamicURl));
    }

    public void shareLink(Uri myDynamicLink) {
        // [START ddl_share_link]
        Intent sendIntent = new Intent();
        String msg = "Hey, check this out: " + myDynamicLink;
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        // [END ddl_share_link]
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            handleDeepLink(intent);
        }
    }

    private void handleDeepLink(Intent intent) {
        FirebaseDynamicLinks.getInstance().getDynamicLink(intent).addOnSuccessListener(pendingDynamicLinkData -> {
            if (pendingDynamicLinkData != null) {
                Uri deepLink = pendingDynamicLinkData.getLink();
                if (deepLink != null) {
                    // todo .....
                }
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null
                        && deepLink != null
                        && deepLink.getBooleanQueryParameter("invitedby", false)) {
                    String referrerUid = deepLink.getQueryParameter("invitedby");
                   Log.e("referrerUid","====-=->"+referrerUid);
                }
            }
        });
    }

    public void  invitationDataClick(View view){
        getInvitation();
    }

    public void getInvitation() {
        // [START ddl_get_invitation]
        FirebaseAnalytics mFirebaseAnalytics;
        mFirebaseAnalytics=FirebaseAnalytics.getInstance(this);

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, pendingDynamicLinkData -> {
                    // Get deep link from result (may be null if no link is found)
                    Uri deepLink = null;

                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink();

                        Log.w("deepLink", "" + deepLink);
                        String cn=String.valueOf(deepLink.getQueryParameters("utm_campaign"));
                        String cm = String.valueOf(deepLink. getQueryParameters("utm_medium"));
                        String cs = String.valueOf(deepLink.getQueryParameters("utm_source"));

                        if (cs != null && cn != null) {
                            Bundle params = new Bundle();
                            params.putString(FirebaseAnalytics.Param.CAMPAIGN, cn);
                            params.putString(FirebaseAnalytics.Param.MEDIUM, cm);
                            params.putString(FirebaseAnalytics.Param.SOURCE, cs);

                            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.CAMPAIGN_DETAILS, params);
                            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, params);
                        }


                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                    }
                });
     /*   FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnCompleteListener(new OnCompleteListener<PendingDynamicLinkData>() {
                    @Override
                    public void onComplete(@NonNull Task<PendingDynamicLinkData> task) {
                        try {
                            if (!task.isSuccessful()) {
                                // Handle error
                                // ...
                            }

                            FirebaseAppInvite invite = FirebaseAppInvite.getInvitation(task.getResult());
                            if (invite != null) {
                                // Handle invite
                                // ...
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });*/
        // [END ddl_get_invitation]
    }

    public void onboardingShare(ShortDynamicLink dl) {
        // [START ddl_onboarding_share]
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Try this amazing app: " + dl.getShortLink());
        startActivity(Intent.createChooser(intent, "Share using"));
        // [END ddl_onboarding_share]
    }

}
