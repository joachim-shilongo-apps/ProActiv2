package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

import blackfundamente.joachim.shilongo.virtualhealthcare.Constants.DatabaseConstants;
import blackfundamente.joachim.shilongo.virtualhealthcare.Constants.StringConstants;
import blackfundamente.joachim.shilongo.virtualhealthcare.Models.Blog;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;

public class CreateNewBlogPost extends AppCompatActivity {
private DatabaseReference blogPostFeed,newEntry;
private EditText blogText;
private Button postSend;
private FirebaseAuth mAuth;

private FirebaseUser mUser;
    private ImageView attachMedia;
    private String userImageLink="";

void initializeViews(){
    blogText=findViewById(R.id.blogEditText);
    postSend=findViewById(R.id.blogSend);
    attachMedia=findViewById(R.id.select_from_camera);
    blogPostFeed= DatabaseConstants.FeedData;
    newEntry=blogPostFeed.child("content");
    mAuth=FirebaseAuth.getInstance();
    mUser=mAuth.getCurrentUser();
}

    private void setSendPost() {
        postSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blog b=new Blog();
                String key=newEntry.push().getKey();
                String writtenText=blogText.getText().toString();
                b.setPostedImage("");
                b.setTextPosted(writtenText);
                b.setUserImage("");
                b.setUsername("");

                b.setUserEmail("");
                assert key != null;
                b.setPostID(key);
                newEntry.child(key).setValue(b).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                    }
                });

            }
        });
        attachMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pix.start(CreateNewBlogPost.this, Options.init()     .setRequestCode(1001));
                    finish();
            }
        });
    }
    private void sendSelectedImageToEdit(String filePath){
        Intent i=new Intent(getApplicationContext(),EditMainFeedImage.class);
        // i.putExtra("SelectedImageUri",filePath);
        Bundle extras=new Bundle();
        extras.putString(StringConstants.postEditImage,filePath);
        i.putExtras(extras);
        //  Toast.makeText(getActivity(),filePath,Toast.LENGTH_LONG).show();
      startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == Activity.RESULT_OK && requestCode == 1001) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            //   Toast.makeText(getActivity(), returnValue.get(0), Toast.LENGTH_LONG).show();
            assert returnValue != null;
            File f = new File(returnValue.get(0));


            sendSelectedImageToEdit( f.getAbsolutePath());
            Uri uri = data.getData();
            Bitmap d = new BitmapDrawable(getResources(), f.getAbsolutePath()).getBitmap();


            super.onActivityResult(requestCode, resultCode, data);


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(this, Options.init().setRequestCode(100));
                } else {
                    Toast.makeText(getApplicationContext(), "Approve permissions to open Pix ImagePicker", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    private void setFileUriExposedProtection() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blog_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViews();
setFileUriExposedProtection();
setSendPost();

    }

}
