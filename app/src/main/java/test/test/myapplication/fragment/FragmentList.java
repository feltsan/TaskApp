package test.test.myapplication.fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import test.test.myapplication.R;
import test.test.myapplication.supp.ListViewAdapter;
import test.test.myapplication.supp.TaskDataBase;

/**
 * Created by john on 13.10.14.
 */
public class FragmentList extends Fragment {
    TaskDataBase dbHelper = null;
    SQLiteDatabase db = null;
    private View rootView;
    private ListView listView;
    private ArrayList<HashMap<String, String>> arrayList;
    private ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.listview_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview);

        dbHelper = new TaskDataBase(rootView.getContext());
        db = dbHelper.getWritableDatabase();

        arrayList = new ArrayList<HashMap<String, String>>();

        Cursor c = db.query(TaskDataBase.TABLE_NAME, new String[]{TaskDataBase.UID, TaskDataBase.NAME,
                TaskDataBase.TEXT, TaskDataBase.SMALL_IMAGE, TaskDataBase.BIG_IMAGE}, null, null, null, null, null);

        if (c.moveToFirst()) {

            do {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", c.getString(c.getColumnIndex(TaskDataBase.NAME)));
                map.put("image", c.getString(c.getColumnIndex(TaskDataBase.SMALL_IMAGE)));
                arrayList.add(map);

                Log.d("myLogs", "ID = " + c.getString(c.getColumnIndex(TaskDataBase.UID)) + ", name = "
                        + c.getString(c.getColumnIndex(TaskDataBase.NAME)) + ", text = " +
                        c.getString(c.getColumnIndex(TaskDataBase.TEXT))
                        + ", s_img = " + c.getString(c.getColumnIndex(TaskDataBase.SMALL_IMAGE)) +
                        ", b_img = " + c.getString(c.getColumnIndex(TaskDataBase.BIG_IMAGE)));

            } while (c.moveToNext());

        } else
            c.close();

        adapter = new ListViewAdapter(rootView.getContext(), arrayList);
        listView.setAdapter(adapter);

        return rootView;
    }
}
