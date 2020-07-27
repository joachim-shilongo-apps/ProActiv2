package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.TutorialActivity;
import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import blackfundamente.joachim.shilongo.virtualhealthcare.Constants.StringConstants;
import blackfundamente.joachim.shilongo.virtualhealthcare.MainActivity;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;
import nouri.in.goodprefslib.GoodPrefs;

public class TutorialScreen extends TutorialActivity {
    private static final int Google_SIGN_IN = 1221;
    private static final int Phone_SIGN_IN = 1223;
    private String displayName, phoneNumber, profilePicture, email;

    List<AuthUI.IdpConfig> google = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build());
    List<AuthUI.IdpConfig> phone = Arrays.asList( new AuthUI.IdpConfig.PhoneBuilder().build());
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Google_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
//            Toast.makeText(getApplicationContext(),response.getError().toString(),  Toast.LENGTH_LONG).show();

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                displayName=user.getDisplayName();
                profilePicture=user.getPhotoUrl().toString();
                email=user.getEmail();
                Toast.makeText(getApplicationContext(),"Loading please wait",Toast.LENGTH_LONG).show();

                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(phone)
                                .build(),
                        Phone_SIGN_IN);
                // ...
            } else {
            }
        }
        if (requestCode == Phone_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);


            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                phoneNumber=user.getPhoneNumber();
                Intent i =new Intent();

                    GoodPrefs.getInstance().saveString(StringConstants.nameKey,displayName.replace(".","_").replace("#","_").replace("$","_").replace("/","_").replace("[","_").replace("]","_"));
                  GoodPrefs.getInstance().saveString(StringConstants.emailKey,email.replace(".","_").replace("#","_").replace("$","_").replace("/","_").replace("[","_").replace("]","_"));
                GoodPrefs.getInstance().saveString(StringConstants.numberKey,user.getPhoneNumber() );
                GoodPrefs.getInstance().saveString(StringConstants.picKey, profilePicture); // For saving Float value


                finish();
                startActivity(i.setClass(TutorialScreen.this, MainActivity.class));

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(
                new Step
                        .Builder()
                        .setTitle("Welcome to ProActive")
                        .setBackgroundColor(Color.parseColor("#F89221"))//#F89221
                        .setDrawable(R.drawable.healthy)
                        .setContent("Thank you for downloading!")
                        .build());
        addFragment(
                new Step
                        .Builder()
                        //  .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                        .setTitle("What is ProActive")
                        .setBackgroundColor(Color.parseColor("#F633A2"))
                        .setDrawable(R.drawable.planning)
                        .setContent("A system that brings health in the comfort of your home, with the rise in covid pandemic, social distancing and self isolation is strongly reccomended and for this reason we bring the doctors to you ")
                        .setSummary("")
                        .build());


        addFragment(
                new Step
                        .Builder()
                        //  .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                        .setTitle("How does it work?")
                        .setBackgroundColor(Color.parseColor("#9D46FF"))
                        .setDrawable(R.drawable.bmi) //#B0003A
                        .setContent("This app is equiped with a set of tools to monitor your health and gives reccomendations")
                        .setSummary("")
                        .build());




    }


    @Override
    public void finishTutorial() {
        // Toast.makeText(this, "Tutorial finished", Toast.LENGTH_SHORT).show();

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(google)
                        .build(),
                Google_SIGN_IN);


    }

    @Override
    public void currentFragmentPosition(int position) {

    }

}