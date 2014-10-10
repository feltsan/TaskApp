package test.test.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import test.test.myapplication.supp.ImageLoader;


public class AboutStudentActivity extends Activity {
    String name;
    String text;
    String image;
    ImageView img;
    ImageLoader imageLoader = new ImageLoader(this);

    TextView tvText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_student);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
       // image = intent.getStringExtra("image");


        TextView tvFName = (TextView) findViewById(R.id.name);
        tvText = (TextView) findViewById(R.id.text);
        img = (ImageView) findViewById(R.id.imageView);
        AsyncJSON asyncJSON = new AsyncJSON();
        asyncJSON.execute();


        tvFName.setText(name);


    }
    private class AsyncJSON extends AsyncTask<Object, Long, JSONObject>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }



        @Override
    protected JSONObject doInBackground(Object[] params) {
            JSONObject jsonArray = new JSONObject();
            HttpRequest request = HttpRequest.get("http://dev.tapptic.com/test/json.php?name="+name);
            if (request.code() == 200){
                String response = request.body();
                try {
                    jsonArray = new JSONObject(response);
                    text = jsonArray.getString("text");
                    image = jsonArray.getString("image");
                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
            return jsonArray;

        }
        protected void onPostExecute(JSONObject  result){
            super.onPostExecute(result);
            tvText.setText(text);
            imageLoader.DisplayImage(image, img);
        }

    }

 }
