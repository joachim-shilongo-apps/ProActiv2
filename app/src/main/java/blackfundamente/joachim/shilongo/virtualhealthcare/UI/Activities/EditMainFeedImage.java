package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.content.*;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.ekalips.fancybuttonproj.FancyButton;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;

import blackfundamente.joachim.shilongo.virtualhealthcare.Constants.DatabaseConstants;
import blackfundamente.joachim.shilongo.virtualhealthcare.Constants.StringConstants;
import blackfundamente.joachim.shilongo.virtualhealthcare.Models.Blog;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;

public class EditMainFeedImage extends Activity {
    DatabaseReference postRoot;
    Uri selectedImageUri;
    ImageView selectedImageView;
    FancyButton postAll;
    EditText captionTheImage;

    private FirebaseAuth mAuth;

    private FirebaseUser mUser;
    private void setUpListeners(){
        try {
            postAll.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    postAll.collapse();

                    startCropping();
                }
            });
        }
        catch (Exception e){
            showToast(e.getMessage());
        }
    }
    private void startCropping() {
        final ProgressDialog progressDialog = new ProgressDialog(EditMainFeedImage.this);
        progressDialog.setTitle("Uploading");
        progressDialog.setCanceledOnTouchOutside(false);
        //  progressDialog.show();



        final StorageReference sr= FirebaseStorage.getInstance().getReference().child("homespital_uploads").child("blog")
                . child(selectedImageUri.getLastPathSegment());
        selectedImageView.setDrawingCacheEnabled(true);
        selectedImageView.buildDrawingCache();
        Bitmap b=((BitmapDrawable)selectedImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,96,baos);
        byte[] data=baos.toByteArray();



        sr.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //   Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();

                        DatabaseReference feedsDatabase = DatabaseConstants.FeedData.child("content");
                      Blog newFeed= new Blog();
                        String key=feedsDatabase.push().getKey();
                        //newFeed.setDate(String.valueOf(System.currentTimeMillis()));
                        newFeed.setTextPosted(captionTheImage.getText().toString());
                        //newFeed.setName(proData.getProName().toString());
                        //newFeed.setPic(proData.getProPic());
                        newFeed.setPostedImage(uri.toString());
                      //  newFeed.setUniqueId(key);
                     ///   newFeed.setToken(proData.getTokenId());
                    //    newFeed.setEmail(proData.getEmail());
                       // HideUtil.hideSoftKeyboard(EditMainFeedImage.this);
                        feedsDatabase.child(key).setValue(newFeed).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();

                                finish();
                                //  new MainActivity().ChangeTabSelection(0);
                                // new MainActivity().ToastWhenDone();
                            }
                        });

                    }

                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Bundle receivedBundle = getIntent().getExtras();

            selectedImageUri = Uri.parse(receivedBundle.getString(StringConstants.postEditImage));
            ///showToast("Received filepath is : ".concat(selectedImageUri.toString()));
            //getIntent().getStringExtra("SelectedImageUri"));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), Objects.requireNonNull(e.getCause()).toString(), Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.post_image_edit_caption);
        selectedImageView = findViewById(R.id.image_to_be_posted_in_main_feed);
        postAll = findViewById(R.id.post_image_to_main_feed);
        captionTheImage = findViewById(R.id.caption_to_be_posted_in_main_feed);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        Toast.makeText(getApplicationContext(),mUser.getDisplayName(), Toast.LENGTH_LONG).show();
        setUpListeners();

        if (selectedImageUri != null) {
            File f = new File(selectedImageUri.toString());
            Bitmap d = new BitmapDrawable(getResources(), f.getAbsolutePath()).getBitmap();

            Glide.with(this).load(d).into(selectedImageView);
        }
    }
}
