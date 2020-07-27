package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import blackfundamente.joachim.shilongo.virtualhealthcare.Adapters.MedicalsAdapter;
import blackfundamente.joachim.shilongo.virtualhealthcare.Models.MedicalItem;
import blackfundamente.joachim.shilongo.virtualhealthcare.R;

public class MedicalFragment extends Fragment implements  MedicalsAdapter.Clickable {
    private List<MedicalItem> creativityList=new ArrayList<>();
    private RecyclerView mCreativeRecyclerView;
    private MedicalsAdapter mAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        MedicalItem c1=new MedicalItem("Baby Care",R.drawable.baby);
        MedicalItem c2=new MedicalItem("Oxygen Supply", R.drawable.oxygen);
        MedicalItem c3=new MedicalItem("Wheel chairs",R.drawable.wheelchair);
        MedicalItem c4=new MedicalItem("Book doctor",R.drawable.book_doctor);

        creativityList.add(c1);
        creativityList.add(c2);
        creativityList.add(c3);

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view =// (View) inflater.inflate(R.layout.activity_create_fragment,container,savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_medical,container,false);
        mCreativeRecyclerView=view.findViewById(R.id.CreativeRecycler);
        mAdapter=new MedicalsAdapter(creativityList,this);

        GridLayoutManager recyclerGrid=new GridLayoutManager(getActivity(),2);
        mCreativeRecyclerView.setLayoutManager(recyclerGrid);
        mCreativeRecyclerView.setAdapter(mAdapter);
      /*
       RecordDialog recordDialog = RecordDialog.newInstance("Record Audio");
        recordDialog.setMessage("Press for record");
        recordDialog.setTitle("Let others hear what you have to say");
                Toast.makeText(getActivity(),"Save audio: " + path, Toast.LENGTH_LONG).show();
            }
        });

        */
        return view; //super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onClickable(int position, TextView textView, ImageView imageView) {
        String creativityText=textView.getText().toString();
        if(creativityText.toLowerCase().contains("vocal")){
           /* VoiceRecordFragment voiceRecordFragment=new VoiceRecordFragment();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.replace(R.id.createRoot,voiceRecordFragment).commit();*/


        }
    }
    public void fragmentExchange(Fragment fragment){

    }
}
