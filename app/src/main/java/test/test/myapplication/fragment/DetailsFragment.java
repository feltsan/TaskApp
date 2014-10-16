package test.test.myapplication.fragment;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import test.test.myapplication.R;
import test.test.myapplication.ThirdLessonActivity;
import test.test.myapplication.supp.ImageLoader;
import test.test.myapplication.supp.TaskDataBase;

/**
 * Created by john on 13.10.14.
 */
public class DetailsFragment extends Fragment {
    String name, text, image;
    ImageView img;
    TaskDataBase dbHelper = null;
    SQLiteDatabase db = null;
    TextView tvText, tvFName;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.about_student, container, false);

        name = "1";

        new AsyncJSON().execute();

        img = (ImageView) rootView.findViewById(R.id.imageView);
        tvText = (TextView) rootView.findViewById(R.id.text);
        tvFName = (TextView) rootView.findViewById(R.id.name);

        dbHelper = new TaskDataBase(rootView.getContext());
        db = dbHelper.getWritableDatabase();
        return rootView;

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
            ImageLoader imageLoader = new ImageLoader(rootView.getContext());
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
