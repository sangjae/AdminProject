package com.example.user.adminproject.notice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommNetwork network = new CommNetwork(this, this);
        JSONObject obj = new JSONObject();

//-------어떤 조건을 JSONObject를 줘야 전부 불러올까..?
        try {
            network.requestToServer("NOTICE_L001", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void onSuccess(String api_key, JSONObject response) {

        try {
            ArrayList<Notice_Value> arrayList = new ArrayList<Notice_Value>();
        /*
        *   리스트안에들어갈내용들을..배열로 받아야되나..받으면 음..다르게넣어야될거같은데
        */
            arrayList.add(new Notice_Value(response.getString("NOTICE_TITLE"), response.getString("NOTICE_CONTENTS")));

            ListView list = (ListView) findViewById(R.id.noticeListView);
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
