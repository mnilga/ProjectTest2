package marianilga.projecttest2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import marianilga.model.Contact;
import marianilga.model.ContactJson;
import marianilga.model.Message;
import marianilga.model.MessageJson;

public class Fragment1 extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private final String TEXT_JSON_MESSAGE =
            "{'data':[{'Message':'Hi, what s up?','From':'UserID1'}," +
                    "{'From':'UserID2','Message':'ok, how are you?'}]}";


    private final String TEXT_JSON =
            "{'data':[{'img':'http://devtest.ad-sys.com/android/01.jpg','name':'Joe','id':'UserID1'}," +
                    "{'name':'Lyla','id':'UserID2','img':'http://devtest.ad-sys.com/android/02.jpg'}," +
                    "{'img':'http://devtest.ad-sys.com/android/03.jpg','name':'Ronit','id':'UserID3'},"+
                    "{'id':'UserID4','name':'Nazer','img':'http://devtest.ad-sys.com/android/04.jpg'},"+
                    "{'name':'Katia','id':'UserID5','img':'http://devtest.ad-sys.com/android/05.jpg'},"+
                    "{'id':'UserID6','name':'Sam','img':'http://devtest.ad-sys.com/android/06.jpg'}]}";


    private ImageView [] imageView;
    private int numberContacts;
    private ArrayList<Contact> contacts;
    private ArrayList<Message> messages;
    private View [] imageMessages;


    public Fragment1 newInstance(int sectionNumber) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.tableLayout);
        LinearLayout.LayoutParams param= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        TableLayout layout = (TableLayout)rootView.findViewById(R.id.tableLayout);


       // Get the list of our contacts.
        ContactJson myJson = new ContactJson(TEXT_JSON);
        contacts = myJson.getContacts();

        // Get the list of our messages.

        MessageJson mesJson = new MessageJson(TEXT_JSON_MESSAGE, contacts);
        messages = mesJson.getMessages();
        imageMessages = new View[messages.size()];

        for (int j = 0; j< messages.size(); j++){
            MyAdapter myAdapter = new MyAdapter(getActivity(), messages.get(j));
            imageMessages[j] = myAdapter.getView();
            imageMessages[j].setOnClickListener(this);
            linearLayout.addView(imageMessages[j], param);
        }


        // Set number of contacts and rows;
        numberContacts = contacts.size();
        int numberRow;
        if (numberContacts % 2 == 0) {
            numberRow = numberContacts / 2;
        }
        else {
            numberRow = numberContacts / 2 + 1;
        }

        TableRow[] row = new TableRow[numberRow];
        TextView [] textName = new TextView [numberContacts];
        imageView = new ImageView [numberContacts];

        int i = 0;
        for (int j = 0; j < numberRow; j++) {
            row[j] = new TableRow(getActivity());
            row[j].setGravity(Gravity.CENTER);

            for (int k = 1; k <= 2; k++) {

                if (i == numberContacts) { break;}

                LinearLayout lay = new LinearLayout(getActivity());
                lay.setOrientation(LinearLayout.VERTICAL);
                TableRow.LayoutParams linLayoutParam = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linLayoutParam.setMargins(30, 30, 30, 30);
                linLayoutParam.gravity = Gravity.CENTER;

                textName[i] = new TextView(getActivity());
                textName[i].setText(contacts.get(i).getName());
                textName[i].setGravity(Gravity.CENTER);

                imageView[i] = new ImageView(getActivity());
                imageView[i].setImageBitmap(contacts.get(i).getImg());
                imageView[i].setLayoutParams(new LinearLayout.LayoutParams(250, 250));
                imageView[i].setOnClickListener(this);

                lay.addView(imageView[i]);
                lay.addView(textName[i]);

                row[j].addView(lay, linLayoutParam);
                i++;
            }
            layout.addView(row[j]);
        }


        Button butCompose = (Button)rootView.findViewById(R.id.but_compose);
        butCompose.setOnClickListener(this);
        return rootView;
    }



    // Create an AlertDialog
    public void showDialog(String title,String message){
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle(title);
        adb.setMessage(message);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setPositiveButton("OK", null);
        AlertDialog alert = adb.create();
        alert.show();
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.but_compose)   {
            showDialog("Clicked On", "Compose");
        }
        else {
            for(int i = 0; i < numberContacts; i++){
                if (v.equals(imageView[i])){
                    showDialog("Clicked On", contacts.get(i).getName());
                }
            }

            for(int j = 0; j < messages.size(); j++){
                if (v.equals(imageMessages[j])){
                    showDialog("Clicked On", "from "+ messages.get(j).getFromId().getName() + " : " +
                            messages.get(j).getTextMessage());
                }
            }
        }

    }
}

