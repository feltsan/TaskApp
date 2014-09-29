package test.test.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutStudentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_student);

        String name = "";
        String sname = "";
        String tell = "";
        String email = "";
        String age = "";

        Intent intent = getIntent();
        if (null != intent){
                name = intent.getStringExtra(ThirdLessonActivity.KEY_NAME);
                sname = intent.getStringExtra(ThirdLessonActivity.KEY_SNAME);
                tell = intent.getStringExtra(ThirdLessonActivity.KEY_TELL);
                email = intent.getStringExtra(ThirdLessonActivity.KEY_EMAIL);
                age = intent.getStringExtra(ThirdLessonActivity.KEY_AGE);
        }
        TextView tvFName = (TextView) findViewById(R.id.first_name_text_view);
        TextView tvSName = (TextView) findViewById(R.id.second_name_text_view);
        TextView tvTell = (TextView) findViewById(R.id.tell_number_text_view);
        TextView tvEmail = (TextView) findViewById(R.id.email_text_view);
        TextView tvAge = (TextView) findViewById(R.id.age_text_view);

        tvFName.setText(name);
        tvSName.setText(sname);
        tvTell.setText(tell);
        tvEmail.setText(email);
        tvAge.setText(age);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
