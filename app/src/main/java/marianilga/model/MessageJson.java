package marianilga.model;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MessageJson {

    public static final String TEXT_MESSAGE = "Message";
    public static final String FROM_ID = "From";
    private String jsonText;
    ArrayList<Contact> contacts = new ArrayList<Contact>();


    public MessageJson(String mJson, ArrayList<Contact> mContacts){
        this.jsonText = mJson;
        this.contacts = mContacts;
    }


    public ArrayList <Message> getMessages(){

        ArrayList <Message> messages = new ArrayList <Message>();

        try {
            JSONObject json = new JSONObject(jsonText);
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i< jsonArray.length(); i++) {
                Message message = new Message();
                message.setTextMessage(jsonArray.getJSONObject(i).getString(TEXT_MESSAGE).toString());
                String id = jsonArray.getJSONObject(i).getString(FROM_ID).toString();
                message.setFromId(getContact(id));
                message.setDate(new Date());

                messages.add(message);
                Log.d("myLogs", " message text =   " );
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("myLogs", " message JSONException =   " + e );
        }

        return messages;
    }


    // Get a contact with necessary id.
    private Contact getContact(String id){
            for (Contact contact : contacts) {
                if (contact.getId().equals(id)) {
                    return contact;
                }
            }
        return null;
    }

}
