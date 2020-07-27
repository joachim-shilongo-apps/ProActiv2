package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


import blackfundamente.joachim.shilongo.virtualhealthcare.Adapters.BlogFeedRecyclerAdapter;
import blackfundamente.joachim.shilongo.virtualhealthcare.Constants.DatabaseConstants;
import blackfundamente.joachim.shilongo.virtualhealthcare.Models.Blog;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Activities.CreateNewBlogPost;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlogFeed.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlogFeed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlogFeed extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference blogFeedData;
    private RecyclerView mRecyclerView;
private BlogFeedRecyclerAdapter mAdapter;
private FloatingActionButton createBlogPost;
    ProgressDialog progressDialog;
    List<Blog> blogFeedList=new ArrayList<>();

    private OnFragmentInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlogFeed.
     */
    // TODO: Rename and change types and number of parameters
    public static BlogFeed newInstance(String param1, String param2) {
        BlogFeed fragment = new BlogFeed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
void setListeners(){
        createBlogPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(), CreateNewBlogPost.class);
                Objects.requireNonNull(getActivity()).startActivity(i);

            }
        });
return;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=inflater.inflate(R.layout.fragment_blog_feed, container, false);
        mRecyclerView=view.findViewById(R.id.blog_feed_recycler);
        createBlogPost=view.findViewById(R.id.fabBlog);
        ProgressDialog progressDialog = new ProgressDialog(getActivity());

        progressDialog.setTitle("Loading blog feed...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        blogFeedData= DatabaseConstants.FeedData;
        blogFeedData.child("content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                blogFeedList.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Blog s=data.getValue(Blog.class);
                 blogFeedList.add(s);
                    mAdapter.notifyDataSetChanged();


                }
                Collections.reverse(blogFeedList);
                progressDialog.hide();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RecyclerView.LayoutManager recyclerGrid=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(recyclerGrid);
        Collections.reverse(blogFeedList);
        //   does=false;
        // Toast.makeText(ScreenShotPost.getContext(),email.replace(".","_"),Toast.LENGTH_LONG).show();



        mAdapter=new BlogFeedRecyclerAdapter(blogFeedList);

        mRecyclerView.setAdapter(mAdapter);
        setListeners();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
