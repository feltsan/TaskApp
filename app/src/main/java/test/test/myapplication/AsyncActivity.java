package test.test.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import com.github.kevinsawicki.http.HttpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AsyncActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        imageView = (ImageView) findViewById(R.id.src_image_view);
        imageView. setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadWithPicasso();

            }
        });
    }


    @Override

    protected void onResume(){
        super.onResume();
        AsyncTestTask asyncTestTask = new AsyncTestTask();
        asyncTestTask.execute();

    }

    private void loadWithPicasso(){

        Picasso.with(this)
            .load("http://inu.tapptic.com/test/image.php?text=%E5%9B%9B&size=60")
            .into(imageView);
    }

   private class AsyncTestTask extends AsyncTask<Object, Long, JSONObject>{
       @Override
       protected void onPreExecute(){
           super.onPreExecute();
       }



       @Override
       protected JSONObject doInBackground(Object[] params) {
           JSONObject jsonArray = new JSONObject();
           HttpRequest request = HttpRequest.get("http://dev.tapptic.com/test/json.php?name=10");
           if (request.code() == 200){
               String response = request.body();
            try {
                jsonArray = new JSONObject(response);
            } catch (JSONException e){
                e.printStackTrace();
            }


           }
           return jsonArray;
   }
       protected void onPostExecute(JSONObject  result){
           super.onPostExecute(result);
           Log.v("Feltsan", result.toString());
       }

   }
}
