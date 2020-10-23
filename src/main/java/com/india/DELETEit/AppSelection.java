package com.india.DELETEit;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Handler;
import android.os.Process;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class AppSelection extends AppCompatActivity implements MyAdapter.CheckBoxCheckedListner {

    Button submit;
    Button Scanapps;


    TextView text ;

    /////////////App listview
    ListView listView;
    CheckBox checkBox;
    SelectedAppList list = new SelectedAppList();

    ArrayList<String> appTitles = new ArrayList<String>();
    ArrayList<Boolean> isChecked = new ArrayList<Boolean>();
    ArrayList<Drawable> icons = new ArrayList<Drawable>();
   TextView sc;
    Animation animFadeIn;
ImageView downarrow ;
TextView totalAppsFound;
TextView  ram ;
    TextView  ramMB ;
    TextView totalRam;
ProgressBar pgr;
ProgressBar loading;
Button share;
    private Handler progressBarHandler = new Handler();

    /////////////App listview


    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_selection);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       // String[] pkg = list.getPackageDB();

//////////////////////////////ProgressBar////////////
ramMB = (TextView) findViewById(R.id.ramMB);
ram = (TextView) findViewById(R.id.ram);
totalRam = (TextView) findViewById(R.id.Totalram);
pgr = (ProgressBar) findViewById(R.id.ramProg);
//pgr.setProgress(100 - Integer.parseInt(getRam()));
//pgr.setSecondaryProgress(100 - Integer.parseInt(getRam()) + 5);
totalRam.setText("TOTAL RAM : "+getTotalRam()+" MB");
ramMB.setText("RAM AVAILABLE(MB) : "+getRamMB()+" MB");
ram.setText("RAM AVAILABLE(%) : "+getRam()+" %");
        loading = (ProgressBar)findViewById(R.id.loading);
        pger(pgr);


/////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////share

        share = (Button)findViewById(R.id.buttonShare);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               showOption();


            }
        });






        ////////////////////////////////////////////////////////




        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeinanim);
        totalAppsFound = (TextView)findViewById(R.id.totalappfound);
        text = (TextView) findViewById(R.id.heading);


        final String[] allpkgs = list.getPackageDB();


        submit = findViewById(R.id.nextButton);
        listView = findViewById(R.id.listView);
        checkBox = findViewById(R.id.checkbox);
        Scanapps = findViewById(R.id.btnScan);
       sc = (TextView)findViewById(R.id.sc);
       final ArrayList<String> finalpkgs = new ArrayList<String>();

    text.startAnimation(animFadeIn);
        downarrow = (ImageView) findViewById(R.id.downarrow);


//////////////////////////////////////////////////////Alert Dialog////////////////////////////////////////



///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Scanapps.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View v) {


           ram.setVisibility(View.GONE);
           ramMB.setVisibility(View.GONE);
           totalRam.setVisibility(View.GONE);
           pgr.setVisibility(View.GONE);
                text.setText("SELECT THE APPS WHICH YOU WANT TO DELETE.");
               Scanapps.animate().alpha(1.0f);
               Scanapps.animate().alpha(0.0f);
               sc.startAnimation(animFadeIn);
               Handler handler = new Handler();
               handler.postDelayed(new Runnable() {
                   public void run() {
                      Scanapps.setVisibility(View.GONE);
                   }
                }, 1050);

               loading.setVisibility(View.VISIBLE);
               pger2(loading);

                share.animate().alpha(1.0f);
                share.animate().alpha(0.0f);
                sc.startAnimation(animFadeIn);
                // listView.setVisibility(View.VISIBLE);
                for (int i = 0 ; i<allpkgs.length;++i) {

                    if (isPackageInstalled(allpkgs[i],getPackageManager())){
                        finalpkgs.add(allpkgs[i]);
                        isChecked.add(false);

                        try
                        {

                          Drawable icon = getApplicationContext().getPackageManager().getApplicationIcon(allpkgs[i]);
                            icons.add(icon);






                            String appName = (String) getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(allpkgs[i], PackageManager.GET_META_DATA));

                            appTitles.add(appName);


                        }
                        catch (PackageManager.NameNotFoundException e)
                        {
                            e.printStackTrace();
                        }


                    }
                }


                if(appTitles.size()==0){

                   Intent i = new Intent(AppSelection.this, NoAppfound.class);
                   startActivity(i);


                }
                else if(appTitles.size() >=5){

                    downarrow.setVisibility(View.VISIBLE);

                    getList(appTitles, isChecked, icons);
                    list.setFinalPackage(finalpkgs);
                    Toast.makeText(getApplicationContext(),"You can scroll down to reveal more apps ",Toast.LENGTH_LONG).show();
                }
                else{
                    getList(appTitles, isChecked, icons);
                    list.setFinalPackage(finalpkgs);


                }

                }




        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // list.addBool(isChecked);

                Intent i = new Intent(AppSelection.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
    private void getList(ArrayList<String> appnameList, ArrayList<Boolean> isChecked, ArrayList<Drawable> appicons) {
        downarrow = (ImageView) findViewById(R.id.downarrow);

        MyAdapter myAdapter = new MyAdapter(this, appnameList, appicons);
        listView.setAdapter(myAdapter);
        myAdapter.setCheckesListner(AppSelection.this);
       // animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
           //     R.anim.fadeinanim);
       // listView.setAnimation(animFadeIn);

        listView.setVisibility(View.VISIBLE);
        listView.setAnimation(animFadeIn);
        String tot = appnameList.size()+" APPS FOUND";
        totalAppsFound.setText(tot);
        totalAppsFound.setVisibility(View.VISIBLE);

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
                        String shareBody = "Greetings my Indian brothers and sisters,\n\nAs we already know about the ongoing movement for boycotting Chinese products and, how buying and using Chinese products is becoming a big problem for our Indian soldiers,who defend the honor of our motherland with their life and blood..\n\nSo on the digital side we can support this movement by deleting applications which are made in China like 'Tiktok', 'Shareit' etc.\nIf you are having problems searching which apps are Chinese which are not..\n...And then removing those apps from your phone..,surely it is a time consuming process.\n\nUnfortunately, Google Play Store removed the app called 'Remove china apps' developed by our Indian developers which was helping out to ease out the process by scanning out all the Chinese apps from user's phone.\n\nAfter reading the news , We thought of developing an app which will carry on this process,till it will be on the Google Play. \n\nI have written the app description in such a way that google will recognise it simply as 'An app remover , made for device optimization'.\n\nSo it would be very much helpful to share this app as much as possible, as from Google Play, Indian users will not be able to recognise its main purpose,So please spread the word with your friends. \n\nOur app 'DELETEit' can scans for more than 250 different Chinese apps and games and even Chinese vendor applications and uninstalls them in a single tap of a button.\n\nI request you to please SHARE this app as much as possible , So people will able to discover it easily.\nThanks A lot, \nJai Hind "+"\uD83C\uDDEE\uD83C\uDDF3"+"\n#BoycottChineseProducts #SonamWangchuk #SoftwareInAWeek #ISupportIndianArmy  " +"\n\n"+"Sonam Wangchuk's Video on Boycotting Chinese Products :\n"+ytlink+"\n\nApp's Google Playstore Link -> \n"+strAppLik;
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
                        String shareBody = "नमस्कार मेरे भारतीय भाइयों और बहनों,\n\nहम पहले से ही चीनी उत्पादों पर प्रतिबंध लगाने के लिए चल रहे आंदोलन के बारे में जानते हैं, की कैसे चीनी उत्पादों को खरीदना और उनका उपयोग करना हमारे भारतीय सैनिकों के लिए एक बड़ी समस्या बन गया है, जो अपने जीवन और रक्त के साथ अपनी मातृभूमि के सम्मान की रक्षा करते हैं।\n\nहम डिजिटल पक्ष में उन ऐप्स को हटाकर इस आंदोलन का समर्थन कर सकते हैं जो चीन में विकसित किये गए है जैसे की : 'टिकटॉक ', 'शेयरीट' आदि हैं। \n\nयदि आपको यह पता लगाने में परेशानी हो रही है कि कौन से अप्प्स चीनी हैं या नहीं हैं। \n...और फिर अपने फोन से उन ऐप्स को हटा रहे हैं.., निश्चित रूप से यह एक समय लेने वाला कार्य है।\n\nदुर्भाग्य से, Google Play Store ने हमारे भारतीय डेवलपर्स द्वारा विकसित 'Remove China Apps' नामक ऐप को हटा दिया है जो उपयोगकर्ता के फ़ोन से सभी चीनी ऐप्स को स्कैन करके हटाने की प्रक्रिया को आसान बनाने में मदद कर रहा था।\n\nखबर पढ़ने के बाद,हमने ऐप विकसित करने के लिए सोचा जो इस प्रक्रिया को आगे बढ़ाने में मदद करेगा , जब तक यह Google Play पर मौजूद रहेगा। \nमैंने एप्लिकेशन डिस्क्रिप्शन को इस तरह से लिखा है कि Google इसे केवल 'एक ऐप रिमूवर,और डिवाइस ऑप्टिमाइज़र ' के रूप में पहचानेगा।\n\n'DELETEit' आपके मोबाइल फ़ोन को स्कैन करता है और आपके फ़ोन से सारे चीनी अप्प्स खोजता और उन्हें एक बटन के टैप में अनइंस्टॉल करता है। यह २५० से ज़्यादा चीनी अप्प्स को स्कैन करने की क्षमता रखता है जिसमे अप्प्स से लेकर चीनी गेम्स भी आतें है और यहां ";
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

    public String getRam(){

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

//Percentage can be calculated for API 16+
        double percentAvail = mi.availMem / (double)mi.totalMem * 100.0;

    return String.valueOf((int)percentAvail);
    }


            public String getTotalRam(){

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);


        return String.valueOf((int)((mi.totalMem)/1e+6));
    }

    public String getRamMB(){

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / 0x100000L;

        return String.valueOf((int)availableMegs);
    }

    private boolean isPackageInstalled(String packagename, PackageManager packageManager) {

            try {
                packageManager.getPackageGids(packagename);
                return true;
            } catch (PackageManager.NameNotFoundException e) {
                return false;
            }
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killApplicationProcess();
    }
    private void killApplicationProcess(){
        Process.killProcess(Process.myPid());
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
    public void getCheckboxCheckedListner(int position) {



        if(isChecked.get(position)){

            isChecked.set(position,false);
            if(isChecked != null){
                   int count = 0;

                   for(int i = 0 ; i<isChecked.size(); ++i) {

                       if(!isChecked.get(i)){
                           count++;
                       }

                       if(count != isChecked.size()){
                           submit.setVisibility(View.VISIBLE);

                       }else{
                           submit.setVisibility(View.INVISIBLE);
                           Toast.makeText(getApplicationContext(), "Select atleast one app to proceed", Toast.LENGTH_SHORT).show();

                       }
                        }
            }
        }else{
            isChecked.set(position,true);
            int count = 0;

            for(int i = 0 ; i<isChecked.size(); ++i) {

                if(!isChecked.get(i)){
                    count++;
                }

                if(count != isChecked.size()){
                    submit.setVisibility(View.VISIBLE);


                }else{
                    submit.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Select atleast one app to proceed", Toast.LENGTH_LONG).show();

                }
            }
        }

        list.addBool(isChecked);


    }

    public void pger(final ProgressBar pgr){
        new Thread(new Runnable() {
            int progressBarStatus = 0;


            public void run() {

                while (progressBarStatus < 100 - Integer.parseInt(getRam()) ) {
                    // performing operation
                    progressBarStatus++;
                    try {
                        Thread.sleep(7);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            pgr.setProgress(progressBarStatus);
                            pgr.setSecondaryProgress(progressBarStatus+5);



                        }
                    });
                }

            }


        }).start();}


    public void pger2(final ProgressBar pgr){
        new Thread(new Runnable() {
            int progressBarStatus = 0;


            public void run() {

                while (progressBarStatus < 100  ) {
                    // performing operation
                    progressBarStatus++;
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            pgr.setProgress(progressBarStatus);
                            pgr.setSecondaryProgress(progressBarStatus+5);



                        }
                    });
                }
                loading.animate().alpha(1.0f);
                loading.animate().alpha(0.0f);

            }


        }).start();}

}







