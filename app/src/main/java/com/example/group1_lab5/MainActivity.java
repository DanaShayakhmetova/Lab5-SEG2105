package com.example.group1_lab5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ActivityResultLauncher<Intent> profileActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK){ Intent data = result.getData();}
                }
            });

    public void changeProfilePic(View view) {
        Intent intent = new Intent(this, ChooseProfile.class);
        profileActivityResultLauncher.launch(intent);
    }

    public void goToGoogleMaps(View view) {
        EditText teamAddress = (EditText) findViewById(R.id.teamAddress);
        Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?q=" + teamAddress.getText());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            ImageView avatarImage = (ImageView) findViewById(R.id.teamFlag);
            String drawableName;
            switch (data.getIntExtra("imageID", R.id.canada)) {
                case R.id.egypt:
                    drawableName = "flag_eg";
                    break;
                case R.id.france:
                    drawableName = "flag_fr";
                    break;
                case R.id.japan:
                    drawableName = "flag_jp";
                    break;
                case R.id.korea:
                    drawableName = "flag_kr";
                    break;
                case R.id.spain:
                    drawableName = "flag_sp";
                    break;
                case R.id.turkey:
                    drawableName = "flag_tr";
                    break;
                case R.id.uk:
                    drawableName = "flag_uk";
                    break;
                case R.id.usa:
                    drawableName = "flag_us";
                    break;
                default:
                    drawableName = "flag_ca";
                    break;
            }

            int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
            avatarImage.setImageResource(resID);
        }

    }
}
