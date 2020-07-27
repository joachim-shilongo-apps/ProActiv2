package blackfundamente.joachim.shilongo.virtualhealthcare.Constants;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConstants {
    public static  final DatabaseReference  FeedData=    FirebaseDatabase.getInstance().getReference().child("BlogFeeds");

}
