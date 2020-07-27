package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blackfundamente.joachim.shilongo.virtualhealthcare.Adapters.PersonalHealthAdapter;
import blackfundamente.joachim.shilongo.virtualhealthcare.Models.PersonalHealthItem;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalHealthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalHealthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalHealthFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PersonalHealthAdapter mAdapter;
    RecyclerView notificationsRecycler;
    List<PersonalHealthItem> notificationsList=new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public PersonalHealthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalHealthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalHealthFragment newInstance(String param1, String param2) {
        PersonalHealthFragment fragment = new PersonalHealthFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_personal_health, container, false);




        mAdapter=new PersonalHealthAdapter(notificationsList);
        mAdapter.notifyDataSetChanged();
        notificationsRecycler=(view).findViewById(R.id.notifictionsRecycler);
        RecyclerView.LayoutManager mLayout=new LinearLayoutManager(getActivity());
        notificationsRecycler.setAdapter(mAdapter);
        notificationsRecycler.setLayoutManager(mLayout);
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
        PersonalHealthItem p1=new PersonalHealthItem();
        p1.setPic(R.drawable.bmi);
        p1.setName("Measure Your Body Mass index");
        PersonalHealthItem p2=new PersonalHealthItem();
       // p2.setPic(R.drawable.pregnant);
       // p2.setName("Pregnancy Tips");
        PersonalHealthItem p3=new PersonalHealthItem();
        p3.setPic(R.drawable.planning);
        p3.setName("Period Tracker");
        PersonalHealthItem p4=new PersonalHealthItem();
        p4.setPic(R.drawable.logout2);
        p4.setName("Logout");

        notificationsList.add(p1);
        notificationsList.add(p2);
        notificationsList.add(p3);
        notificationsList.add(p4);
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
