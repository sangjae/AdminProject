package com.example.user.adminproject.notice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.adminproject.R;
import com.example.user.adminproject.common.CommNetwork;
import com.example.user.adminproject.common.NoticeAdapter;
import com.example.user.adminproject.common.listener.onNetworkResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 2016-08-18.
 */
public class NoticeList extends AppCompatActivity implements onNetworkResponseListener{

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_list);


        CommNetwork network = new CommNetwork(this, this);
        JSONObject obj = new JSONObject();

//-------어떤 조건을 JSONObject를 줘야 전부 불러올까..?
        try {
            addToolBar();
            network.requestToServer("NOTICE_L001", obj);

            list = (ListView) findViewById(R.id.noticeListView);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NoticeList.this, NoticeView.class);
                    intent.putExtra("NOTICE_SEQ", position);
                    startActivity(intent);
                    finish();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void addToolBar() throws Exception {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.text_notice);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void toolbarRightButtonClick(View v){
        Intent intent = new Intent(NoticeList.this, NoticeNew.class);
        startActivity(intent);
    }




    @Override
    public void onSuccess(String api_key, JSONObject response) {

        try {
            ArrayList<Notice_Value> arrayList = new ArrayList<Notice_Value>();
        /*
        *   리스트안에들어갈내용들을..배열로 받아야되나..받으면 음..다르게넣어야될거같은데
        */
            arrayList.add(new Notice_Value(response.getString("NOTICE_TITLE"), response.getString("NOTICE_CONTENTS")));

            list = (ListView) findViewById(R.id.noticeListView);
            NoticeAdapter adapter = new NoticeAdapter(this, R.layout.notice_list, arrayList);
            list.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(String api_key, String error_cd, String error_msg) {

    }
}
