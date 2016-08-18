package com.example.user.adminproject.common.listener;

import org.json.JSONObject;

/**
 * Created by user on 2016-08-18.
 */
public interface onNetworkResponseListener {

        void onSuccess(String api_key, JSONObject response);
        void onFailure(String api_key, String error_cd, String error_msg);

}
