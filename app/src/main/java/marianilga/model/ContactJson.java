package marianilga.model;

import android.graphics.Bitmap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ContactJson {

    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String IMG = "img";

    private String jsonText;
    private Bitmap bitmap;

    public ContactJson(String mJson){
        this.jsonText = mJson;
    }


    public ArrayList <Contact> getContacts(){

        ArrayList <Contact> contacts = new ArrayList <Contact>();
        String link;

        try {
            JSONObject json = new JSONObject(jsonText);
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i< jsonArray.length(); i++) {
                Contact contact = new Contact();
                contact.setName(jsonArray.getJSONObject(i).getString(NAME).toString());
                contact.setId(jsonArray.getJSONObject(i).getString(ID).toString());
                link = jsonArray.getJSONObject(i).getString(IMG).toString();
                ImageDownloader imageDownloader = new ImageDownloader(link);
                imageDownloader.execute();

                bitmap = imageDownloader.get();
                contact.setImg(bitmap);

                contacts.add(contact);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return contacts;
    }


}
