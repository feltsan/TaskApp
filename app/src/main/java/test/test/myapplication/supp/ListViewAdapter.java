package test.test.myapplication.supp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;
import java.util.HashMap;

import test.test.myapplication.AboutStudentActivity;
import test.test.myapplication.AsyncActivity;
import test.test.myapplication.R;
import test.test.myapplication.ThirdLessonActivity;


/**
 * Created by BruSD on 9/23/2014.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList <HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();
    LayoutInflater inflater;
    ViewHolder holder;

    public ListViewAdapter(Context context, ArrayList <HashMap<String,String>> arrayList) {
        this.context = context;
        data = arrayList;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int positions) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        ImageView image_view;
        TextView name;
        Button select_button;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {



       if(convertView == null) {
           inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.item_list_view_layout, null);
           holder = new ViewHolder();

           holder.image_view = (ImageView) convertView.findViewById(R.id.image_view);
           holder.name = (TextView) convertView.findViewById(R.id.first_name_text_view);
           holder.select_button = (Button) convertView.findViewById(R.id.button_on_LW);
           convertView.setTag(holder);
       }else{
           holder = (ViewHolder) convertView.getTag();
       }


        resultp = data.get(position);
        holder.name.setText(resultp.get(ThirdLessonActivity.KEY_NAME));
        Picasso.with(context).load(resultp.get(ThirdLessonActivity.KEY_IMAGE)).into(holder.image_view);
        holder.select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                resultp = data.get(position);
                Intent intent = new Intent(context, AboutStudentActivity.class);
                intent.putExtra("name", resultp.get(ThirdLessonActivity.KEY_NAME));
                intent.putExtra("image", resultp.get(ThirdLessonActivity.KEY_IMAGE));
                context.startActivity(intent);

            }
        });

        return convertView;
    }

}
