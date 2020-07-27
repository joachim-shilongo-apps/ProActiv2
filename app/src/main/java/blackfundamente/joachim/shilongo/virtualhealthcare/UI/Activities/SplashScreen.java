package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;

import blackfundamente.joachim.shilongo.virtualhealthcare.MainActivity;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;

public class SplashScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(() -> {

            mAuth=FirebaseAuth.getInstance();
            try{

                if(mAuth.getCurrentUser()!=null){
                    // Toast.makeText(getApplicationContext(),"Logged in as "+mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_LONG).show();
                    Intent i=new Intent();
                    startActivity(i.setClass(SplashScreen.this, MainActivity.class));

                    finish();
                }
                else {
                    // Toast.makeText(getApplicationContext(),"User not logged in",Toast.LENGTH_LONG).show();
                    Intent i=new Intent();
                    startActivity(i.setClass(SplashScreen.this,TutorialScreen.class));
                    finish();
                }
            }catch(Exception e){}
            //FirebaseUser user=FirebaseUser.
        },2500);

    }
}