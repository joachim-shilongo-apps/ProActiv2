package blackfundamente.joachim.shilongo.virtualhealthcare;

import android.net.Uri;
import android.os.Bundle;

import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.BlogFeed;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.ContactsFragment;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.MedicalFragment;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.MenstrualTracker;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.PersonalHealthFragment;
import blackfundamente.joachim.shilongo.virtualhealthcare.UI.Fragments.PharmacyShop;
import me.riddhimanadib.library.BottomBarHolderActivity;
import me.riddhimanadib.library.NavigationPage;
public class MainActivity extends BottomBarHolderActivity implements FirstFragment.OnFragmentInteractionListener,BlogFeed.OnFragmentInteractionListener,
        MenstrualTracker.OnFragmentInteractionListener, PersonalHealthFragment.OnFragmentInteractionListener,ContactsFragment.OnFragmentInteractionListener

{
// TODO FOLLOW MVVM PATTERN; HENCE PROTOTYPE  WE GET IT TO WORK IN EASY FAST WAY AS POSSIBLE BUT WE WILL LEAVE PACKAGES AVAILABLE FOR SEAMLESS INTEGRATION
   private BubbleTabBar bubbleTabBar;
   private ImageView cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       bubbleTabBar=findViewById(R.id.bubbleTabBar);
        cart =findViewById(R.id.ifxLog3o);
       bubbleTabBar.addBubbleListener(new OnBubbleClickListener() {
           @Override
           public void onBubbleClick(int i) {
               FragmentManager fm=getSupportFragmentManager();
               FragmentTransaction ft=fm.beginTransaction();
               switch(i){
                   case R.id.home:
                       BlogFeed blogFeed=new BlogFeed();

                       ft.replace(R.id.fragInsert,blogFeed);
                       ft.addToBackStack("");
                       ft.commit();
                       break;


                   case R.id.medicalbottom:
                       MedicalFragment medicalFragment=new MedicalFragment();
                       ft.replace(R.id.fragInsert,medicalFragment);
                       ft.addToBackStack("");
                       ft.commit();
                           break;
                   case R.id.profile_bottom:
                       PersonalHealthFragment personalHealthFragment=new PersonalHealthFragment();
                       ft.replace(R.id.fragInsert,personalHealthFragment);
                       ft.addToBackStack("");
                       ft.commit();
                       break;
                   case R.id.cont:

                       ContactsFragment cf=new ContactsFragment();
                       ft.replace(R.id.fragInsert,cf);
                       ft.addToBackStack("");
                       ft.commit();
                       break;

               }
           }
       });
       bubbleTabBar.setSelected(0,true);
       cart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentManager fm=getSupportFragmentManager();
               FragmentTransaction ft=fm.beginTransaction();

               PharmacyShop ps=new PharmacyShop();
               ft.replace(R.id.fragInsert,ps);
               ft.addToBackStack("");
               ft.commit();
           }
       });
        //INITIALIZE BOTTOM NAVIGATION ITEMS USING THIRD PARTY LIBRARY

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
