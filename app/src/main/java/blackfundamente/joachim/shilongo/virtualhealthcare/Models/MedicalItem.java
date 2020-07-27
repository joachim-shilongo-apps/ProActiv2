package blackfundamente.joachim.shilongo.virtualhealthcare.Models;

public class MedicalItem {

    private String title;
    private int picture;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }


    public MedicalItem(String title, int picture) {
        this.title = title;
        this.picture = picture;
    }
}
