package blackfundamente.joachim.shilongo.virtualhealthcare.Adapters;
// MedicalsAdapter
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import blackfundamente.joachim.shilongo.virtualhealthcare.Models.MedicalItem;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;

public class MedicalsAdapter extends RecyclerView.Adapter<MedicalsAdapter.ViewHolder> {

    public static Clickable onClickable;
    public interface Clickable{
        public void onClickable(int position, TextView textView, ImageView imageView);
    }
    private Context mContext;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView featuredpic;
        public TextView medicalassistancedescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            featuredpic = (itemView).findViewById(R.id.medicalItemPicture);
            medicalassistancedescription = (itemView).findViewById(R.id.medicalItemText);

            itemView.setOnClickListener(new  View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(onClickable!=null){
                        onClickable.onClickable(getAdapterPosition(), medicalassistancedescription,featuredpic);
                    }
                }
            });
        }
    }

    protected List<MedicalItem> creativityList;

    public MedicalsAdapter(List<MedicalItem> creativityList,Clickable onClickable) {
        this.creativityList = creativityList;
        this.onClickable=onClickable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_item_llayout,null);
        ViewHolder viewHolder = new ViewHolder(itemLayout);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
      MedicalItem data = creativityList.get(position);
        holder.medicalassistancedescription.setText(data.getTitle());
        Glide.with(mContext).load(data.getPicture()).into(holder.featuredpic);
        holder.featuredpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "latoyashivute@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, data.getTitle());
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            mContext.startActivity(Intent.createChooser(emailIntent, "Choose app to send Request"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return creativityList.size();
    }


}