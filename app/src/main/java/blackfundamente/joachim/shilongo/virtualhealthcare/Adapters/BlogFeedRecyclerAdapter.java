package blackfundamente.joachim.shilongo.virtualhealthcare.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import blackfundamente.joachim.shilongo.virtualhealthcare.Models.Blog;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;

public class BlogFeedRecyclerAdapter extends RecyclerView.Adapter<BlogFeedRecyclerAdapter.ViewHolder> {
    public List<Blog> BlogList=new ArrayList<>();
    private Context mContext;

    public BlogFeedRecyclerAdapter(List<Blog> BlogList) {
        this.BlogList = BlogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayout= LayoutInflater.from(parent.getContext()).inflate( R.layout.blog_feed_item,null);
        ViewHolder viewHolder=new ViewHolder(itemLayout);
        mContext=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Blog blogItem=BlogList.get(position);
        holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "latoyashivute@yahoo.com"));
                emailIntent.putExtra(Intent.EXTRA_TEXT, blogItem.getTextPosted());
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Post reply");
                mContext.startActivity(Intent.createChooser(emailIntent, "Choose app to reply post"));
            }
        });
        try {

            holder.screenshot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            if(blogItem.getPostedImage().isEmpty()){
                holder.screenshot.setVisibility(View.GONE);
            }
            else{
                holder.screenshot.setVisibility(View.VISIBLE);
            }
            Glide.with( holder.screenshot).load(blogItem.getPostedImage()).into(holder.screenshot);//.setImageDrawable(mContext.getDrawable(result.getImage()));
            holder.caption.setText(blogItem.getTextPosted());}
        catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return BlogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView screenshot;
        public TextView caption;
        public TextView reply;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caption=(itemView).findViewById(R.id.blogCaption);
            screenshot=(itemView).findViewById(R.id.blogPostedPic);
            reply=(itemView).findViewById(R.id.reply_via_email );

        }
    }
}
