package marianilga.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mnilg_000 on 6/29/2015.
 */
public class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {

    private String link;
    private Bitmap bitmap;

    ImageDownloader (String mLink){
        this.link = mLink;
    }


    @Override
    protected Bitmap doInBackground(Void... params) {

        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bit) {

        super.onPostExecute(bit);
    }

}
