package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.savvi.rangedatepicker.CalendarPickerView;

import blackfundamente.joachim.shilongo.virtualhealthcare.R;


import com.savvi.rangedatepicker.CalendarPickerView;
import com.savvi.rangedatepicker.SubTitle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenstrualTracker.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenstrualTracker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenstrualTracker extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button viewPrediction;
    TextView dataPrediction;
    ArrayList<Date> arrayList=new ArrayList<>(0);

    private OnFragmentInteractionListener mListener;
    CalendarPickerView calendar;
    Date startMenstrual,endMenstrual;
    Calendar cycle;
    public MenstrualTracker() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenstrualTracker.
     */
    // TODO: Rename and change types and number of parameters
    public static MenstrualTracker newInstance(String param1, String param2) {
        MenstrualTracker fragment = new MenstrualTracker();
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
        View view= inflater.inflate(R.layout.fragment_menstrual_tracker, container, false);
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);
cycle=Calendar.getInstance();
        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, - 10);

        calendar = view.findViewById(R.id.calendar_view);
        viewPrediction=view.findViewById(R.id.viewPrediction);
        dataPrediction=view.findViewById(R.id.predictionData);
        viewPrediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.clearHighlightedDates();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

                startMenstrual=  calendar.getSelectedDates().get(0);
                cycle.setTime(startMenstrual);
               Calendar j=Calendar.getInstance();
               j.setTime(startMenstrual);
               j.add(Calendar.DAY_OF_MONTH,14);
                Calendar k=Calendar.getInstance();
                k.add(Calendar.DAY_OF_MONTH,27);
                arrayList.add(startMenstrual);
                    arrayList.add(j.getTime());
               // List<Date> allDates = new ArrayList();

                dataPrediction.setText("Fertility is most Likely to occur between "+cycle.getTime().toString()+" and "+j.getTime().toString()+" and likely to end on "+k.getTime());

               // Toast.makeText(getActivity(),"Start Day "+startMenstrual.toString() + " end day "+j.toString()  , Toast.LENGTH_LONG ).show();
                calendar.highlightDates(arrayList);


               //getSubTitles();

            }
        });
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);

       // calendar.deactivateDates(list);
       arrayList = new ArrayList<>();
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

            String strdate = "22-4-2019";
            String strdate2 = "26-4-2019";

            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            arrayList.add(newdate);
            arrayList.add(newdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM yyyy", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
               // .withDeactivateDates(list)
                //.withSubTitles(getSubTitles())
                .withHighlightedDates(arrayList);

        calendar.scrollToDate(new Date());

 return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private ArrayList<SubTitle> getSubTitles() {
        final ArrayList<SubTitle> subTitles = new ArrayList<>();
       // final Calendar tmrw = Calendar.getInstance();

        cycle.add(Calendar.DATE, 28);
        subTitles.add(new SubTitle(cycle.getTime(), "Periods End"));
        return subTitles;
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
