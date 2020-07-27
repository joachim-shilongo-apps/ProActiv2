package blackfundamente.joachim.shilongo.virtualhealthcare.Repositories;

import android.content.*;

import com.google.firebase.iid.*;

public class profile_data {
    String proPic, tokenId, proName;
    String type;

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    String email;
    String uniqueId;
    //mGoogleSign = new GoogleSign(this,this);
    SharedPreferences pic, name, iid;
    //drawer= (DrawerLayout) findViewById(R.id.drawer_layout);


    Context c;

    public profile_data(Context c) {
        this.c = c;
    }

    public String replaceAllChars(String s) {
        String processed = s.replace("@yahoo.com", "_").replace("@", "_").replace("#", "_").replace("*", "_").replace(",", "_").replace(".", "_").replace("!", "_").replace("$", "_").replace("/", "_").replace("\\", "_");


        return
                processed;
    }

    public void setEmail(String email) {
        this.email = replaceAllChars(email);
    }

    public String getEmail() {
        pic = c.getSharedPreferences("pic", 0);
        email = pic.getString("email", null).replace(".", "_").toLowerCase();

        return email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        pic = c.getSharedPreferences("pic", 0);
        type = pic.getString("type", null);

        return type;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public void logOut() {
        pic = c.getSharedPreferences("pic", 0);
        pic.edit().clear().commit();
    }

    public String getProPic() {
        pic = c.getSharedPreferences("pic", 0);
        proPic = pic.getString("pic", null);
        //iid.getString("instace_id",null);


        return proPic;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        pic = c.getSharedPreferences("pic", 0);
        email = pic.getString("token", null).replace(".", "_").toLowerCase();

        return tokenId;
    }

    public void setProName(String proName) {
        this.proName = replaceAllChars(proName);
    }

    public String getProName() {
        pic = c.getSharedPreferences("pic", 0);
        proName = pic.getString("name", null).replace(".", "_").toLowerCase();

        return proName;
    }

    public String getUniqueId() {
        pic = c.getSharedPreferences("pic", 0);
        proName = pic.getString("uniqueId", null).replace(".", "_").toLowerCase();

        return uniqueId;
    }


}
