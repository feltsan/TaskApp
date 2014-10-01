package test.test.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View.OnClickListener;

import test.test.myapplication.supp.Student;
import test.test.myapplication.supp.StudentAdapter;

/**
 * Created by BruSD on 9/23/2014.
 */
public class ThirdLessonActivity extends Activity {
    private ListView listView;
    private Button button1;
    private ArrayList<HashMap<String, Student>> student = new ArrayList<HashMap<String, Student>>();
    private StudentAdapter studentAdapter;


    public static final String KEY_NAME = "name";
    public static final String KEY_SNAME = "sname";
    public static final String KEY_TELL = "tell";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_AGE = "age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third_lesson_layout);
        listView = (ListView) findViewById(R.id.custom_items_list_view);
        generateStudentList();

    }


    @Override
    protected void onResume() {
        super.onResume();

        studentAdapter = new StudentAdapter(this, student, R.layout.item_list_view_layout, null, null);
        listView.setAdapter(studentAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }


    private void generateStudentList() {


        HashMap<String, Student> item = new HashMap<String, Student>();

        item.put("student",new Student("Юлія", "Горкій", "99999", "my@gmail", "24"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Михайло", "Тромбола", "777777", "sy@gmail", "23"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Олександр", "Мікуланінець", "66666666", "hhy@gmail", "22"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Іван", "Фельцан", "11111111111", "f.i@gmail", "23"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Михайло", "Рогач", "222222222", "f.i@gmail", "21"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Александр", "Миченко", "44444444444", "f.i@gmail", "23"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Олег", "Магобей", "5555555", "f.i@gmail", "18"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Діана", "Ручкайте", "11111111111", "f.i@gmail", "23"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Саша", "Курта", "11111111111", "f.i@gmail", "23"));
        student.add(item);

        item = new HashMap<String, Student>();

        item.put("student",new Student("Сергей", "Грищук", "11111111111", "f.i@gmail", "23"));
        student.add(item);


    }


}

