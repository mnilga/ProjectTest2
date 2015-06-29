package marianilga.projecttest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import marianilga.model.ContactJson;
import marianilga.model.Message;

public class MyAdapter{

   private Context context;
   private Message message;
   private LayoutInflater inflater;

   public MyAdapter(Context context, Message message){
       this.context = context;
       this.message = message;
       inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

   }

    public View getView() {

        View view  = inflater.inflate(R.layout.item_list, null);
        TextView textName = (TextView)view.findViewById(R.id.textName);
        textName.setText(message.getFromId().getName());

        TextView textMessage = (TextView)view.findViewById(R.id.textMessage);
        textMessage.setText(message.getTextMessage());

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageBitmap(message.getFromId().getImg());

        TextView textTime = (TextView)view.findViewById(R.id.time);
        SimpleDateFormat timeFormat = new SimpleDateFormat("H:m");
        textTime.setText(timeFormat.format(message.getDate()));

        return view;
    }
}
