package blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import blackfundamente.joachim.shilongo.virtualhealthcare.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PharmacyShop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PharmacyShop extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public PharmacyShop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PharmacyShop.
     */
    // TODO: Rename and change types and number of parameters
    public static PharmacyShop newInstance(String param1, String param2) {
        PharmacyShop fragment = new PharmacyShop();
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


        View view=inflater.inflate(R.layout.fragment_pharmacy_shop, container, false);

        WebView wv = (WebView) view.findViewById(R.id.webView);

        wv.setWebViewClient(new WebViewClient());

       wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
       wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        wv.setWebViewClient(new WebViewClient());

        wv.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.setTitle("Loading Store...");
                progressDialog.setMessage("Please wait...");
               // progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressDialog != null){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);

            }

        });
        wv.loadUrl("https://city-pharmacy-windhoek.myshopify.com/");

        Toast.makeText(getActivity(),"Loading ",Toast.LENGTH_LONG).show();
        return view;
    }

}
