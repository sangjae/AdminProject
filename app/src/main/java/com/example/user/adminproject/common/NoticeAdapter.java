package com.example.user.adminproject.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.adminproject.R;
import com.example.user.adminproject.notice.Notice_Value;

import java.util.List;

/**
 * Created by user on 2016-08-18.
 */
public class NoticeAdapter extends ArrayAdapter<Notice_Value> {
    private List<Notice_Value> items;
    private LayoutInflater inflater;


    public NoticeAdapter(Context context, int textViewResourceId, List<Notice_Value> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.notice_list_item, null);
        }
        Notice_Value item = (Notice_Value) items.get(position);

        TextView tv_noticeTitle = (TextView) convertView.findViewById(R.id.tv_noticeTitle);
        TextView tv_noticeContent = (TextView) convertView.findViewById(R.id.tv_noticeContent);

        tv_noticeTitle.setText(item.getTitle());
        tv_noticeContent.setText(item.getContents());



        return convertView;
    }


}
