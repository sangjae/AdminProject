package com.example.user.adminproject.expense;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.user.adminproject.R;

/**
 * Created by SangJae on 2016-08-23.
 */
public class ExpenseManagerView extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_manager_list);


    }
}
