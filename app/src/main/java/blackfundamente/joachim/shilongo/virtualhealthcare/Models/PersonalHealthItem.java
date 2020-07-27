package blackfundamente.joachim.shilongo.virtualhealthcare.Models;

import java.util.HashMap;

public class PersonalHealthItem {
    public boolean isVerified() {
    return verified;
}

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    //Map
    boolean verified;
    String videoLink;
    String type;
    String postedPic;
    String uniqueId;
    String imagePic;
    String name;
    String date;
    String msg;
    int pic;
    String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;
    HashMap<String,String> comments;
    public PersonalHealthItem(){

    }

    public void setVideoLink(String videoLink){
        this.videoLink=videoLink;
    }
    public String getVideoLink(){
        return this.videoLink;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setPostedPic(String postedPic)
    {
        this.postedPic = postedPic;
    }

    public String getPostedPic()
    {
        return postedPic;
    }

    public void setUniqueId(String uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    public String getUniqueId()
    {
        return uniqueId;
    }

    public void setComments(HashMap<String, String> comments)
    {
        this.comments = comments;
    }

    public HashMap<String, String> getComments()
    {
        return comments;
    }



    public void setImagePic(String imagePic){
        this.imagePic=imagePic;
    }
    public String getImagePic(){
        return imagePic;
    }
    public void setToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setPic(int pic)
    {
        this.pic = pic;
    }

    public int getPic()
    {
        return pic;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getDate(){
        return date;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

}
