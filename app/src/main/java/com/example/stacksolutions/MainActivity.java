package com.example.stacksolutions;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText stackSearch;
    private Button searchBtn;
    private String text;
    private static final String BASE_URL = "https://api.stackexchange.com/2.2/questions";
    private ArrayList<Questions> stackArrayList;
    private String exception;
    private ListView questionList;
    private StackAdapter stackAdapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        stackSearch = findViewById(R.id.book_genre);
        searchBtn = findViewById(R.id.btn_search);
        questionList = findViewById(R.id.book_list);
        stackArrayList = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("kunal","clicked");
                text = stackSearch.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                stackArrayList.clear();
                new StackAsyncTask().execute();

            }
        });
    }

    class StackAsyncTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            Uri uri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter("order","desc")
                    .appendQueryParameter("sort","activity")
                    .appendQueryParameter("tagged",text)
                    .appendQueryParameter("site","stackoverflow")
                    .build();
            URL url;
            try {
                url = new URL(uri.toString());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder buffer = new StringBuilder();
                String line = reader.readLine();
                while(line != null){
                    buffer.append(line);
                    line = reader.readLine();
                }
                JSONObject questions = new JSONObject(buffer.toString());
                JSONArray items = questions.getJSONArray("items");
                for(int i=0;i<30;i++) {
                    JSONObject first = items.getJSONObject(i);
                    JSONObject owner = first.getJSONObject("owner");
                    String profile_image = owner.getString("profile_image");
                    String display_name = owner.getString("display_name");
                    String view_count = first.getString("view_count");
                    String answer_count = first.getString("answer_count");
                    String score = first.getString("score");
                    String last_activity_date = first.getString("last_activity_date");
                    String creation_date = first.getString("creation_date");
                    String link = first.getString("link");
                    String title = first.getString("title");
                    stackArrayList.add(new Questions(display_name, title, link, profile_image, view_count, answer_count, last_activity_date, creation_date, score));
                }
            }catch (Exception e){
                exception = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressBar.setVisibility(View.GONE);
            stackAdapter = new StackAdapter(MainActivity.this,R.layout.questionlistitem, stackArrayList);
            questionList.setAdapter(stackAdapter);
        }
    }
}
