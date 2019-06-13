package com.mrlonewolfer.ndtvappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAsyncTaskAdapter.OnResponseListener,NewsCustomAdapterList.OnNewsClickListener {

    ListView listView;
    String url="http://feeds.feedburner.com/ndtvsports-latest";
    ArrayList<NewsBean> newsArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    NewsCustomAdapterList newsCustomAdapterList;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        newsArrayList= new ArrayList<>();


        MyAsyncTaskAdapter myAsyncTaskAdapter=new MyAsyncTaskAdapter(context,url,this);
        myAsyncTaskAdapter.execute();

    }


    @Override
    public void onResponse(ArrayList<NewsBean> newsBeanArrayList) {

        newsCustomAdapterList=new NewsCustomAdapterList(context,newsBeanArrayList,this);
        recyclerView.setAdapter(newsCustomAdapterList);
    }

    @Override
    public void onUserClick(ArrayList<NewsBean> newsBeanArrayList) {
        Toast.makeText(context, "You Click on News", Toast.LENGTH_SHORT).show();
    }
}
