package test.test.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import test.test.myapplication.supp.TaskDataBase;

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
    TaskDataBase dbHelper;
    SQLiteDatabase db;

    public static final String KEY_NAME = "name";
    public static final String KEY_TEXT = "text";
    public static final String KEY_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview_main);
  //      new DownloadJSON().execute();
        dbHelper = new TaskDataBase(this);
        db = dbHelper.getWritableDatabase();
        arrayList = new ArrayList<HashMap<String, String>>();



        // ContentValues cv = new ContentValues();

        Cursor c = db.query(TaskDataBase.TABLE_NAME,new String[]{TaskDataBase.UID, TaskDataBase.NAME,
                TaskDataBase.TEXT, TaskDataBase.SMALL_IMAGE, TaskDataBase.BIG_IMAGE },null, null, null, null, null);

        if (c.moveToFirst()){

        do{

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", c.getString(c.getColumnIndex(TaskDataBase.NAME)));
            map.put("image", c.getString(c.getColumnIndex(TaskDataBase.SMALL_IMAGE)));
            arrayList.add(map);

            Log.d("myLogs", "ID = " + c.getString(c.getColumnIndex(TaskDataBase.UID)) + ", name = "
                    + c.getString(c.getColumnIndex(TaskDataBase.NAME)) + ", text = " +
                    c.getString(c.getColumnIndex(TaskDataBase.TEXT))
                    + ", s_img = " + c.getString(c.getColumnIndex(TaskDataBase.SMALL_IMAGE)) +
                    ", b_img = " + c.getString(c.getColumnIndex(TaskDataBase.BIG_IMAGE)));

        }while (c.moveToNext());

        }else
            c.close();

        listView = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter(ThirdLessonActivity.this, arrayList);
        listView.setAdapter(adapter);
    //    db.delete("task_table", null, null);
      //  dbHelper.close();
      //  db.close();



//     mProgressDialog.dismiss();
    }

//    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//            mProgressDialog = new ProgressDialog(ThirdLessonActivity.this);
//            mProgressDialog.setMessage("Loading...");
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            arrayList = new ArrayList<HashMap<String, String>>();
//            jsonArray = JSONFunctions.getJSONfromURL("http://dev.tapptic.com/test/json.php");
//
//            try {
//                    for (int i = 0; i<jsonArray.length(); i++){
//                    //HashMap<String, String> map = new HashMap<String, String>();
//                    ContentValues cv = new ContentValues();
//                    jsonObject = jsonArray.getJSONObject(i);
//                   // map.put("name", jsonObject.getString("name"));
//                    cv.put(TaskDataBase.NAME, jsonObject.getString("name"));
//                   // map.put("image", jsonObject.getString("image"));
//                    cv.put(TaskDataBase.SMALL_IMAGE, jsonObject.getString("image"));
//                   // cv.put(TaskDataBase.BIG_IMAGE, jsonObject.getString("image"));
//                        db.insert(TaskDataBase.TABLE_NAME, null, cv);
//
//
//
//                     //   arrayList.add(map);
//
//                }
//
//            }catch (JSONException e ){
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return null;
//        }
//        protected void onPostExecute(Void args){
//
//
//        }
//
//}

}