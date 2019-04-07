package com.example.stacksolutions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StackAdapter extends ArrayAdapter {

    private int resourceLayout;
    private Context mContext;

    public StackAdapter(Context context, int resource,List objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        final Questions questions = (Questions) getItem(position);

        TextView title = v.findViewById(R.id.title);
        TextView views = v.findViewById(R.id.view_count);
        TextView score = v.findViewById(R.id.score);
        TextView answers = v.findViewById(R.id.answers_count);
        TextView author = v.findViewById(R.id.name);
        TextView last = v.findViewById(R.id.last_date);
        TextView creation = v.findViewById(R.id.creation_date);
        TextView link = v.findViewById(R.id.link);
        ImageView image = v.findViewById(R.id.image);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date creation_date = new Date(TimeUnit.SECONDS.toMillis(Long.parseLong(questions.getCreation())));
        Date last_date = new Date(TimeUnit.SECONDS.toMillis(Long.parseLong(questions.getLast_activity())));
        title.setText(questions.getTitle());
        views.setText("views"+":"+questions.getView_count());
        score.setText("score"+":"+questions.getScore());
        answers.setText("answers"+":"+questions.getAnswer_count());
        author.setText(questions.getOwner());
        last.setText("Creation:"+simpleDateFormat.format(creation_date));
        creation.setText("Last Activity:"+simpleDateFormat.format(last_date));
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(questions.getLink())));
            }
        });
        try {
            URL url = new URL(questions.getImage());
            Glide.with(getContext()).load(url.toString()).into(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(Integer.parseInt(questions.getAnswer_count())<= 0){
            v.setBackgroundColor(getContext().getResources().getColor(R.color.red));
        }else{
            v.setBackgroundColor(getContext().getResources().getColor(R.color.green));
        }
        return v;
    }
}

