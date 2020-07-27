package blackfundamente.joachim.shilongo.virtualhealthcare.Models;

public class Blog {
    String username, textPosted, userImage, postedImage,userEmail;

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    String postID;

    public Blog(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTextPosted() {
        return textPosted;
    }

    public void setTextPosted(String textPosted) {
        this.textPosted = textPosted;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPostedImage() {
        return postedImage;
    }

    public void setPostedImage(String postedImage) {
        this.postedImage = postedImage;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
