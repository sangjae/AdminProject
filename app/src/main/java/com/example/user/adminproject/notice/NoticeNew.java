package com.example.user.adminproject.notice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.adminproject.R;
import com.example.user.adminproject.common.CommNetwork;
import com.example.user.adminproject.common.listener.onNetworkResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2016-08-18.
 */
public class NoticeNew extends AppCompatActivity implements onNetworkResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.notice_new);
            addToolBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToolBar() throws Exception {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.text_notice_add);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private boolean emptyCheck(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            Toast.makeText(this, getString(R.string.error_msg_required_values_are_missing), Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    public void toolbarRightButtonClick(View v){

        EditText noticeTitle = (EditText) findViewById(R.id.et_NoticeTitle);
        EditText noticeContents = (EditText) findViewById(R.id.et_NoticeContents);

        if ( emptyCheck(noticeTitle)
                || emptyCheck(noticeContents)
                ) {
            return;
        }
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("NOTICE_TITLE", noticeTitle.getText().toString());
            requestObject.put("NOTICE_CONTENTS", noticeContents.getText().toString());

            CommNetwork network = new CommNetwork(this, this);
            network.requestToServer("NOTICE_I001", requestObject);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onSuccess(String api_key, JSONObject response) {

        if(api_key.equals("NOTICE_I001")){
            Intent intent = new Intent(NoticeNew.this, NoticeList.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onFailure(String api_key, String error_cd, String error_msg) {
        Log.d("sangjaeTest", "failure");
    }
}
