package marianilga.model;

import android.graphics.Bitmap;

/**
 * Created by mnilg_000 on 6/28/2015.
 */
public class Contact {

    private String name;
    private String id;
    private Bitmap img;


    public void setName(String mName){
        this.name = mName;
    }

    public void setId(String mId){
        this.id = mId;
    }

    public void setImg(Bitmap mImg){
        this.img = mImg;
    }

    public Bitmap getImg(){
        return img;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

}
