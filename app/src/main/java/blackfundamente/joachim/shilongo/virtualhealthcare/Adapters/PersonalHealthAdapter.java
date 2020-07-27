package blackfundamente.joachim.shilongo.virtualhealthcare.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import blackfundamente.joachim.shilongo.virtualhealthcare.MainActivity;
import blackfundamente.joachim.shilongo.virtualhealthcare.Models.PersonalHealthItem;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Activities.SplashScreen;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.BMIFragment;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.MedicalFragment;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.MenstrualTracker;
import de.hdodenhof.circleimageview.CircleImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalHealthAdapter extends RecyclerView.Adapter<PersonalHealthAdapter.ViewHolder>  {

    List<PersonalHealthItem > notificationsList=new ArrayList<>();
    Context context;
    private FirebaseAuth mAuth;
    iOSDialogBuilder ios;


    public PersonalHealthAdapter(List<PersonalHealthItem> notificationsList) {
        this.notificationsList = notificationsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayout=(View) LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_health_item,parent,false);
        context=parent.getContext();
        ViewHolder viewHolder=new ViewHolder(itemLayout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PersonalHealthItem notification=notificationsList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notification.getName().toLowerCase().contains("out")){



                    ios = new iOSDialogBuilder(((MainActivity)context));
                    ios.setTitle("Logout:")
                            .setSubtitle("Do you want to logout?")
                            .setBoldPositiveLabel(true)
                            .setCancelable(true)
                            .setPositiveListener("Yes", new iOSDialogClickListener() {
                                @Override
                                public void onClick(iOSDialog dialog) {
                                     FirebaseAuth.getInstance().signOut();
                                    //finish();
                                    Intent i =new Intent(((MainActivity)context), SplashScreen.class);
                                    ((MainActivity)context).startActivity(i);
                                    dialog.dismiss();


                                }
                            })

                            .setNegativeListener("No", new iOSDialogClickListener() {
                                @Override
                                public void onClick(iOSDialog dialog) {


                                    dialog.dismiss();

                                }
                            })
                            .build().show();



                }
              else  if(notification.getName().toLowerCase().contains("period")){
                    FragmentManager fm=((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();

                    MenstrualTracker menstrualTracker=new MenstrualTracker();
                    ft.replace(R.id.fragInsert,menstrualTracker);
                    ft.addToBackStack("");
                    ft.commit();
                }

                else  if(notification.getName().toLowerCase().contains("mass")){
                    FragmentManager fm=((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();

                   BMIFragment bmi=new BMIFragment();
                    ft.replace(R.id.fragInsert,bmi);
                    ft.addToBackStack("");
                    ft.commit();
                }
               // ((MainActivity)context).startActivity(new Intent().setClass((MainActivity)context, CommentsFragment.class).putExtra("PostData",notification.getMsg()).putExtra("unique",notification.getUniqueId())
                 //       .putExtra("name",notification.getName()).putExtra("email",notification.getEmail()));

            }
        });
        holder.notificationText.setText(notification.getName());
        Glide.with(holder.notifThumbnail.getContext()).load(notification.getPic()).into(holder.notifThumbnail);
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView notificationText;
        CircleImageView notifThumbnail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationText=(itemView).findViewById(R.id.notificationText);
            notifThumbnail=(itemView).findViewById(R.id.notificationThumbnail);
            notificationText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
