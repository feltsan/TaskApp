package test.test.myapplication.supp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import test.test.myapplication.AboutStudentActivity;
import test.test.myapplication.R;
import test.test.myapplication.ThirdLessonActivity;


/**
 * Created by BruSD on 9/23/2014.
 */
public class StudentAdapter extends SimpleAdapter {

    private ArrayList<HashMap<String, Student>> student;
    private LayoutInflater layoutInflater;
    private Context context;
    private int resource;

//    /**
//     * Constructor fegt
//     *
//     * @param context  The context where the View associated with this SimpleAdapter is running
//     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
//     *                 Maps contain the data for each row, and should include all the entries specified in
//     *                 "from"
//     * @param resource Resource identifier of a view layout that defines the views for this list
//     *                 item. The layout file should include at least those named views defined in "to"
//     * @param from     A list of column names that will be added to the Map associated with each
//     *                 item.
//     * @param to       The views that should display column in the "from" parameter. These should all be
//     *                 TextViews. The first N views in this list are given the values of the first N columns
//     */
    public StudentAdapter(Context context, ArrayList<HashMap<String, Student>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.student = data;
        this.context = context;
        this.resource = resource;
    }

    public Object getSelect( int position){
        return student.get(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(resource, parent, false);


        TextView nameTextView = (TextView) convertView.findViewById(R.id.first_name_text_view);
        nameTextView.setText(student.get(position).get("student").getName());

        TextView snameTextView = (TextView) convertView.findViewById(R.id.second_name_text_view);
        snameTextView.setText(student.get(position).get("student").getSname());

        Button button = (Button) convertView.findViewById(R.id.button_on_LW);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, AboutStudentActivity.class);


                Student select = (Student) student.get(position).get("student");
                intent.putExtra(ThirdLessonActivity.KEY_NAME,select.getName());
                intent.putExtra(ThirdLessonActivity.KEY_SNAME,select.getSname());
                intent.putExtra(ThirdLessonActivity.KEY_TELL,select.getTell());
                intent.putExtra(ThirdLessonActivity.KEY_EMAIL,select.getEmail());
                intent.putExtra(ThirdLessonActivity.KEY_AGE,select.getAge());

                context.startActivity(intent);
            }
        });


        return convertView;
    }
}
