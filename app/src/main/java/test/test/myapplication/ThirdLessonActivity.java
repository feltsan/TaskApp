package test.test.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

//import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.test.myapplication.supp.JSONFunctions;
import test.test.myapplication.supp.ListViewAdapter;
import test.test.myapplication.supp.Student;

/**
 * Created by BruSD on 9/23/2014.
 */
public class ThirdLessonActivity extends Activity {
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private ListView listView;
    private ArrayList<HashMap<String,String>> arrayList;
    private ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    public static final String KEY_NAME = "name";
    public static final String KEY_SNAME = "sname";
    public static final String KEY_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview_main);

        new DownloadJSON().execute();

    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(ThirdLessonActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            arrayList = new ArrayList<HashMap<String, String>>();
            jsonArray = JSONFunctions.getJSONfromURL("http://dev.tapptic.com/test/json.php");

            try {
                    for (int i = 0; i<jsonArray.length(); i++){
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonObject = jsonArray.getJSONObject(i);
                    map.put("name", jsonObject.getString("name"));
                    map.put("image", jsonObject.getString("image"));
                    arrayList.add(map);

                }

            }catch (JSONException e ){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void args){
            listView = (ListView) findViewById(R.id.listview);
            adapter = new ListViewAdapter(ThirdLessonActivity.this, arrayList);
            listView.setAdapter(adapter);
            mProgressDialog.dismiss();
        }

    }

}

