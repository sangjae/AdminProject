package com.example.user.adminproject.expense;

/**
 * Created by SangJae on 2016-08-23.
 */
public class ExpenseManagerValue {

    String userName;
    String state;
    String use;
    String storeName;
    String amount;
    String requestDay;

    public ExpenseManagerValue(String userName, String state, String use, String storeName, String amount, String requestDay){
        this.userName = userName;
        this.state = state;
        this.use = use;
        this.storeName = storeName;
        this.amount = amount;
        this.requestDay = requestDay;
    }

}
