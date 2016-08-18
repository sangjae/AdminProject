package com.example.user.adminproject.login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.adminproject.R;
import com.example.user.adminproject.notice.NoticeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by user on 2016-08-18.
 */
public class AdminLogin extends AppCompatActivity{

    private Context CONTEXT;
    EditText edit_login;
    EditText edit_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CONTEXT = this;

        Button btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_login = (EditText) findViewById(R.id.id_edit);
                edit_pw = (EditText) findViewById(R.id.pw_edit);

                String id = edit_login.getText().toString();
                String pw = edit_pw.getText().toString();

                TextView tv = (TextView) findViewById(R.id.textview1);
                tv.setText("id=" + id + "\npw=" + pw);

                //new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "get_gateway_post_kr.php", "nationalid="+getNationalId());
                new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "login_do_get.php?id="+id+"&pwd="+pw, "");


            }
        });



        /**
        *    회원가입하는 페이지로 이동시킬때
        */
        Button btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent1 = new Intent(AdminLogin.this, Sign_up.class);
                //startActivity(intent1);
            }
        });

    }


    //AsyncTask<param,Progress,Result>
    private class httpTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... args) {

            String returnValue = "";
            HttpURLConnection conn = null;
            try {
                Log.e("!!!", "args[0] = " + args[0]);
                Log.e("!!!", "args[1] = " + args[1]);
                String urlString = "http://www.matescorp.com/soyu/" + args[0];
                Log.e("!!!", "urlString = " + urlString);
                URL url = new URL(urlString);

                // open connection
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);            // 입력스트림 사용여부
                conn.setDoOutput(false);            // 출력스트림 사용여부
                conn.setUseCaches(false);        // 캐시사용 여부
                conn.setReadTimeout(3000);        // 타임아웃 설정 ms단위
                conn.setRequestMethod("GET");  // or GET
//              conn.setRequestMethod("POST");
/*
                // POST 값 전달 하기
                StringBuffer params = new StringBuffer("");
//                params.append("name=" + URLEncoder.encode(name)); //한글일 경우 URL인코딩
                params.append(args[1]);
                PrintWriter output = new PrintWriter(conn.getOutputStream());
                output.print(params.toString());
                output.close();
*/
                // Response받기
                StringBuffer sb = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                for (; ; ) {
                    String line = br.readLine();
                    if (line == null) break;
                    sb.append(line + "\n");
                }

                br.close();
                conn.disconnect();
                br = null;
                conn = null;

                returnValue = sb.toString();
            } catch (ConnectException e) {
                e.printStackTrace();
                return "ConnectException|" + args[0] + "|" + args[1];
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                return "SocketTimeoutException|" + args[0] + "|" + args[1];
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn!=null) {
                    conn.disconnect();
                }
            }
//            return args[0]+returnValue;
            return returnValue;
        }

        @Override
        protected void onPostExecute(String result) {
            result = result.trim();
            //Log.e("!!!", "httpTask result = | " + result + " |");
            Log.d("logtest1", result);
            if (result.trim().equals("") || result.trim().equals("[]") || result.trim().equals("null")) {
                Log.e("!!!", "------");
                //Toast.makeText(CONTEXT, getResources().getString(R.string.map_empty), Toast.LENGTH_SHORT).show();


                return;
            }  else {
                try {
                    //Toast.makeText(CONTEXT, result, Toast.LENGTH_SHORT).show();
                    Log.d("logtest2", result);


                    String str1 = result.toString();
                    Log.d("test1",str1);
                    String a = "success";


                    if(result.equals(a)){

                        String id = edit_login.getText().toString();
                        String pw = edit_pw.getText().toString();

                        //Intent intent = new Intent(MainActivity.this, Sign_up.class);
                        Intent intent = new Intent(AdminLogin.this, NoticeList.class);
                        intent.putExtra("id", id);
                        intent.putExtra("pw", pw);

                        startActivity(intent);
                    }
                    else {
                        Log.d("test3",str1);
                        Toast.makeText(CONTEXT, "아이디나 패스워드를 잘못쳤습니다.", Toast.LENGTH_SHORT).show();

                    }





                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
    }

}
