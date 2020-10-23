package com.india.DELETEit;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int countleftApps = 0;
    Button submit;
    SelectedAppList list;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      list = new SelectedAppList();



        SelectedAppList list = new SelectedAppList();
        submit = findViewById(R.id.nextButton);
        String[] packageListAll = list.getFinalPackage().toArray(new String[0]);
        final ArrayList<String> sortedPkglist = new ArrayList<String>();

        ArrayList<Boolean> boolList = list.getBools();
       // Toast.makeText(MainActivity.this,"Boolist is null", Toast.LENGTH_LONG).show();

        if (!boolList.isEmpty()){
            for (int i = 0; i < boolList.size(); ++i) {
                if (boolList.get(i)) {
                    sortedPkglist.add(packageListAll[i]);
                    countleftApps++;
                } }}else{
                Toast.makeText(MainActivity.this,"BoolListEmpty : "+boolList.toString(), Toast.LENGTH_LONG).show();

            }

        final ImageButton uninstall = (ImageButton) findViewById(R.id.uninstall);

        uninstall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                // com.paullipnyagov.drumpads24
            PackageManager pm = getPackageManager();
            ArrayList<String> finalPkgs = new ArrayList<String>();
            for (int i=0 ; i < sortedPkglist.size();i++){
           if (isPackageInstalled(sortedPkglist.get(i), pm)){
             finalPkgs.add(sortedPkglist.get(i));
           } else{
               Toast.makeText(MainActivity.this,sortedPkglist.get(i)+" is not installed in this phone.", Toast.LENGTH_LONG).show();
           }}

            uninstallApps(finalPkgs);


            }
        });
}
private boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageGids(packagename);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    private void uninstallApps(ArrayList<String> packageList) {

        for (int i = 0 ; i <packageList.size();i++ ){
            Uri uri = Uri.fromParts("package",packageList.get(i), null);
            Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, uri);
            // store result
            intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
            startActivityForResult(intent, 1);

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get result
        if(resultCode == RESULT_OK){


            countleftApps--;
            if (countleftApps==0) {



                Intent i = new Intent(MainActivity.this, ThankYou.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Thanks for the contribution :)", Toast.LENGTH_LONG).show();
                Log.d("Result", "onActivityResult: OK");
            }
        }else if (resultCode == RESULT_CANCELED){
            Toast.makeText(MainActivity.this,"Clicked cancelled .", Toast.LENGTH_LONG).show();

            Log.d("Result", "onActivityResult: CANCEL");
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
      //  Toast.makeText(getApplicationContext(),"DELETEit : Application Paused",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // Toast.makeText(getApplicationContext(),"DELETEit : Application Resumed",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);


        builder.setMessage("Do you want to go to app selection screen?");


        builder.setTitle("Going Back ?");

        builder.setCancelable(false);

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                               Intent i = new Intent(MainActivity.this,AppSelection.class);
                               startActivity(i);
                            }
                        });


        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }



}

