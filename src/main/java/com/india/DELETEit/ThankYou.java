package com.india.DELETEit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class ThankYou extends AppCompatActivity {
 Animation fadein;
    private InterstitialAd mInterstitialAd;
Button share ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        share = (Button) findViewById(R.id.buttonShareYt);


        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-2638923967276930/7134108855");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        showDialog();


        fadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeinanim);

        TextView thnx = (TextView) findViewById(R.id.Thnxtext);
        thnx.startAnimation(fadein);

        Button dltbtn = (Button) findViewById(R.id.Delete);

        dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:com.india.DELETEit"));
                startActivity(intent);
            }
        });

        try {

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showOption();
                }
            });

        }catch (Exception e ){
            Toast.makeText(getApplicationContext(), "Cannot able to share due to some error", Toast.LENGTH_SHORT).show();
        }

    }


    void showDialog(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Can I show an ad ? :)");

        //Setting message manually and performing action on button click
        builder.setMessage("I am a solo developer, viewing a single ad will help a lot.")
                .setCancelable(true)
                .setPositiveButton("Yes,Sure.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                       try {

                           if (mInterstitialAd.isLoaded()) {
                               Toast.makeText(getApplicationContext(), "Ad loaded", Toast.LENGTH_SHORT).show();
                               Log.d("TAG", "The interstitial Loaded.");


                               mInterstitialAd.show();


                           } else {
                               Log.d("TAG", "The interstitial wasn't loaded yet.");
                               Toast.makeText(getApplicationContext(), "The ad is not loaded yet", Toast.LENGTH_SHORT).show();

                           }

                           mInterstitialAd.setAdListener(new AdListener() {
                               @Override
                               public void onAdClosed() {

                               }

                           });

                           Log.d("TAG", "The interstitial Loaded.");

                           Toast.makeText(getApplicationContext(), "Thank You! :)", Toast.LENGTH_SHORT).show();
                       }catch (Exception e){

                           Log.d("TAG", "The interstitial no Loaded , error.");
                           Toast.makeText(getApplicationContext(), "Could not able to load ads ", Toast.LENGTH_SHORT).show();
                       }




                    }
                })
                .setNegativeButton("No!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Can I show an ad ? :) ");
        alert.show();
    }


    void showOption(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Set sharing language : ");

        //Setting message manually and performing action on button click
        builder.setMessage("Select in which language the preset message body should be in:")
                .setCancelable(true)
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ///////////////////////Yes

                        Intent share = new Intent(Intent.ACTION_SEND);
                        final  String pkgname = "com.india.DELETEit";
                        String strAppLik = "";



                        try{
                            strAppLik = "https://play.google.com/store/apps/details?id="+pkgname;
                        }catch (android.content.ActivityNotFoundException anfe ){
                            strAppLik = "https://play.google.com/store/apps/details?id="+pkgname;

                        }
                        share.setType("text/link");
                        String ytlink = "https://www.youtube.com/watch?v=7Zt4fB1lwIo";
                        String shareBody = "Greetings my Indian brothers and sisters,\n\nAs we already know about the ongoing movement for boycotting Chinese products and, how buying and using Chinese products is becoming a big problem for our Indian soldiers,who defend the honor of our motherland with their life and blood..\n\nSo on the digital side we can support this movement by deleting applications which are made in China like 'Tiktok', 'Shareit' etc.\nIf you are having problems searching which apps are Chinese which are not..\n...And then removing those apps from your phone..,surely it is a time consuming process.\n\nUnfortunately, Google Play Store removed the app called 'Remove china apps' developed by our Indian developers which was helping out to ease out the process by scanning out all the Chinese apps from user's phone.\n\nAfter reading the news , We thought of developing an app which will carry on this process,till it will be on the Google Play. \n\nI have written the app description in such a way that google will recognise it simply as 'An app remover , made for device optimization'.\n\nSo it would be very much helpful to share this app as much as possible, as from Google Play, Indian users will not be able to recognise its main purpose,So please spread the word with your friends. \n\nApp 'DELETEit' scans for more than 240 different Chinese apps and games and even Chinese vendor applications and uninstalls them in a single tap of a button.\n\nI request you to please SHARE this app as much as possible , So people will able to discover it.\nThanks A lot, \nJai Hind "+"\uD83C\uDDEE\uD83C\uDDF3"+"\n#BoycottChineseProducts #SonamWangchuk #SoftwareInAWeek #ISupportIndianArmy  " +"\n\n"+"Sonam Wangchuk's Video on Boycotting Chinese Products :\n"+ytlink+"\n\nApp's Google Playstore Link -> \n"+strAppLik;
                        String shareSub = "APP NAME";
                        share.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                        share.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(Intent.createChooser(share,"SHARE THIS APP USING :"));
                        Toast.makeText(getApplicationContext(),"Thank you for spreading the word , it will help a lot!",Toast.LENGTH_SHORT).show();


                    }
                })
                .setNegativeButton("Hindi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ///////////////////No


                        Intent share = new Intent(Intent.ACTION_SEND);
                        final  String pkgname = "com.india.DELETEit";
                        String strAppLik = "";



                        try{
                            strAppLik = "https://play.google.com/store/apps/details?id="+pkgname;
                        }catch (android.content.ActivityNotFoundException anfe ){
                            strAppLik = "https://play.google.com/store/apps/details?id="+pkgname;

                        }
                        share.setType("text/link");
                        String ytlink = "https://www.youtube.com/watch?v=7Zt4fB1lwIo";
                        String shareBody = "नमस्कार मेरे भारतीय भाइयों और बहनों,\n\nहम पहले से ही चीनी उत्पादों पर प्रतिबंध लगाने के लिए चल रहे आंदोलन के बारे में जानते हैं, की कैसे चीनी उत्पादों को खरीदना और उनका उपयोग करना हमारे भारतीय सैनिकों के लिए एक बड़ी समस्या बन गया है, जो अपने जीवन और रक्त के साथ अपनी मातृभूमि के सम्मान की रक्षा करते हैं।\n\nहम डिजिटल पक्ष में उन ऐप्स को हटाकर इस आंदोलन का समर्थन कर सकते हैं जो चीन में विकसित किये गए है जैसे की : 'टिकटॉक ', 'शेयरीट' आदि हैं। \n\nयदि आपको यह पता लगाने में परेशानी हो रही है कि कौन से अप्प्स चीनी हैं या नहीं हैं। \n...और फिर अपने फोन से उन ऐप्स को हटा रहे हैं.., निश्चित रूप से यह एक समय लेने वाला कार्य है।\n\nहम यह जानते हैं की, दुर्भाग्य से, Google Play Store ने हमारे भारतीय डेवलपर्स द्वारा विकसित 'Remove China Apps' नामक ऐप को हटा दिया है जो उपयोगकर्ता के फ़ोन से सभी चीनी ऐप्स को स्कैन करके हटाने की प्रक्रिया को आसान बनाने में मदद कर रहा था।\n\nखबर पढ़ने के बाद, मैंने एक ऐप विकसित करने के लिए सोचा जो इस प्रक्रिया को आगे बढ़ाने में मदद करेगा , जब तक यह Google Play पर मौजूद रहेगा। \nमैंने एप्लिकेशन डिस्क्रिप्शन को इस तरह से लिखा है कि Google इसे केवल 'एक ऐप रिमूवर,और डिवाइस ऑप्टिमाइज़र ' के रूप में पहचानेगा।\n\n'DELETEit' आपके मोबाइल फ़ोन को स्कैन करता है और आपके फ़ोन से सारे चीनी अप्प्स खोजता और उन्हें एक बटन के टैप में अनइंस्टॉल करता है। यह २५० से ज़्यादा चीनी अप्प्स को स्कैन करने की क्षमता रखता है जिसमे अप्प्स से लेकर चीनी गेम्स भी आतें है और यहां ";
                        String shareLast = "तक कि चीनी विक्रेता अप्प्स को भी खोज सकता ह। \n\n मैं आपसे अनुरोध करता हूं कि कृपया इस ऐप को अधिक से अधिक शेयर करें और हमारा सन्देश आगे बढ़ाए। \n\n"+"धन्यवाद्,\nजय हिंद"+" \uD83C\uDDEE\uD83C\uDDF3 "+"\n#BoycottChineseProducts #SonamWangchuk #SoftwareInAWeek #ISupportIndianArmy" +"\n\n"+"Sonam Wangchuk's Video on Boycotting Chinese Products :\n"+ytlink+"\n\nApp's Google Playstore Link -> \n\n"+strAppLik;
                        String shareSub = "APP NAME";
                        share.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                        share.putExtra(Intent.EXTRA_TEXT,shareBody+shareLast);
                        startActivity(Intent.createChooser(share,"SHARE THIS APP USING :"));
                        Toast.makeText(getApplicationContext(),"Thank you for spreading the word , it will help a lot!",Toast.LENGTH_SHORT).show();


                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Set sharing language : ");
        alert.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
