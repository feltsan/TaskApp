package test.test.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;

import test.test.myapplication.supp.ImageLoader;
import test.test.myapplication.supp.TaskDataBase;


public class AboutStudentActivity extends Activity {
    String name;
    String text;
    String image;
    ImageView img;
    TaskDataBase dbHelper = null;
    SQLiteDatabase db = null;
    TextView tvText, tvFName;
   ImageLoader imageLoader = new ImageLoader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_student);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        new AsyncJSON().execute();

        img = (ImageView) findViewById(R.id.imageView);
        tvText = (TextView) findViewById(R.id.text);
        tvFName = (TextView) findViewById(R.id.name);

        dbHelper = new TaskDataBase(this);
        db = dbHelper.getWritableDatabase();

    }

    private class AsyncJSON extends AsyncTask<Object, Long, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected JSONObject doInBackground(Object[] params) {
            JSONObject jsonArray = new JSONObject();
            ContentValues cv = new ContentValues();
            HttpRequest request = HttpRequest.get("http://dev.tapptic.com/test/json.php?name=" + name);
            if (request.code() == 200) {
                String response = request.body();
                try {
                    jsonArray = new JSONObject(response);
                    text = jsonArray.getString("text");
                    cv.put(ThirdLessonActivity.KEY_TEXT, text);
                    image = jsonArray.getString("image");
                    cv.put(TaskDataBase.BIG_IMAGE, image);
                    db.update(TaskDataBase.TABLE_NAME, cv, "name = ?", new String[]{name});


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return jsonArray;

        }

        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            Cursor c = db.query(TaskDataBase.TABLE_NAME, new String[]{TaskDataBase.UID, TaskDataBase.NAME,
                    TaskDataBase.TEXT, TaskDataBase.SMALL_IMAGE, TaskDataBase.BIG_IMAGE}, "name = ?", new String[]{name}, null, null, null);

            c.moveToFirst();

            tvText.setText(c.getString(c.getColumnIndex(TaskDataBase.TEXT)));
            tvFName.setText(c.getString(c.getColumnIndex(TaskDataBase.NAME)));
            imageLoader.DisplayImage(c.getString(c.getColumnIndex(TaskDataBase.BIG_IMAGE)), img);

            c.close();

        }
    }

}
