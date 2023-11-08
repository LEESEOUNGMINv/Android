package com.example.ex13.signin;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
// import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// 기존 import 삭제후 다시 import
import androidx.annotation.Nullable;

import com.example.ex13.HttpClient;
import com.example.ex13.mypage.MyPageMainActivity;
import com.example.ex13.R;
import com.example.ex13.Web;
import com.example.ex13.dto.CustomerDTO;
import com.google.gson.Gson;
//


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by psn on 2018-01-18.
 */

public class SignInActivity extends Activity {

    protected static final String TAG = SignInActivity.class.getSimpleName();
    private Gson mGson;
    private Web web;
    private Call<String> mCallId;
    private Map<String, String> map;
    EditText edtId, edtPwd;
    Button btnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Toast.makeText(getApplicationContext(), "onCreate  ~~~~`", Toast.LENGTH_SHORT).show();

        edtId = (EditText) findViewById(R.id.edt_id);
        edtPwd = (EditText) findViewById(R.id.edt_pwd);
        btnSignIn = (Button) findViewById(R.id.btn_signin);

        // 로그인 버튼 클릭
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그인 클릭", Toast.LENGTH_SHORT).show();
                InnerTask task = new InnerTask();
                Map<String, String> map = new HashMap<>();
                map.put("id", edtId.getText().toString());
                map.put("password", edtPwd.getText().toString());
                Log.d("아이디 ------", edtId.getText().toString());
                Log.d("패스워드 ------", edtPwd.getText().toString());
                Toast.makeText(getApplicationContext(), "onCreate  ~~~~`", Toast.LENGTH_SHORT).show();

              task.execute(map);   // doInBackground() 실행
            }
        });

       // actionBar();   //  extends Activity 상속시 지원안되는 기능이 많음
    }

    public void actionBar() {
        // 변경
        android.app.ActionBar bar = getActionBar();
        bar.setDisplayOptions(bar.DISPLAY_SHOW_CUSTOM);
        //
        bar.setCustomView(R.layout.custom_bar);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.custom_bar, null);
        TextView tv_bar = (TextView) v.findViewById(R.id.tv_bar);
        tv_bar.setText("로그인");
        bar.setCustomView(v);
    }

    //각 Activity 마다 Task 작성
    public class InnerTask extends AsyncTask<Map, Integer, String> {

        //doInBackground 실행되기 이전에 동작
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //작업을 쓰레드로 처리 .. execute()로 호출
        @Override
        protected String doInBackground(Map... maps) {

            //HTTP 요청 준비
            HttpClient.Builder http = new HttpClient.Builder("POST", Web.servletURL + "androidSignIn"); //스프링 컨트롤러 requestMapping("androidSignIn")            //파라미터 전송
            http.addAllParameters(maps[0]);  // 입력한 데이터(id, 패스워드)

            //HTTP 요청 전송
            HttpClient post = http.create();
            post.request();  // 컨트롤러 호출

            // 안드로이드에서 응답받음
            String body = post.getBody(); //Web의 Controller에서 리턴한 값
            Log.d("body------", body);
            return body;
        }

        //doInBackground 종료되면 동작
        /**
         * @param s : doInBackground에서 리턴한 body. JSON 데이터
         */
        @Override
        protected void onPostExecute(String s) {
            Log.d("JSON_RESULT", s);

            //JSON으로 받은 데이터를 VO Obejct로 바꿔준다.
            if(s.length() > 0) {
                Gson gson = new Gson();
                CustomerDTO vo = gson.fromJson(s, CustomerDTO.class);  // json 데이터를 DTO로 변경
                if (vo.getId() != null) {
                    Intent intent = new Intent(SignInActivity.this, MyPageMainActivity.class);
                    intent.putExtra("id", vo.getId());   // putExtra("key", value); ==> 매개변수 역할
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "가입 인증이 필요한 회원입니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
