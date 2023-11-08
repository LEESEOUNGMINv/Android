package com.example.ex13.mypage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
// 기존 import 삭제후 다시 import
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
//
import com.example.ex13.HttpClient;
import com.example.ex13.R;
import com.example.ex13.Web;
import com.google.gson.Gson;

/**
 * Created by psn on 2018-02-07.
 */

public class MyPageMainActivity extends AppCompatActivity {

    InnerTask task = null;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent intent = getIntent();
        id = intent.getStringExtra("id"); //  intent.getStringExtra("putExtra의 key")

        task = new InnerTask();
        task.execute(id);  // doInBackground() 호출

        actionBar();  // extends AppCompatActivity 시 사용가능
    }

    // 리뷰 버튼 클릭

    // 회원 수정 버튼 클릭

    // 결산 버튼 클릭

    // 예약 버튼 클릭

    private void actionBar() {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_bar, null);
        TextView tv_bar = v.findViewById(R.id.tv_bar);
        tv_bar.setText("MyProject");

        ActionBar bar = getSupportActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        bar.setCustomView(v);
    }

    private class InnerTask extends AsyncTask {

        //MypageRecyAdapter adapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            HttpClient.Builder http = new HttpClient.Builder("POST", Web.servletURL + "androidMyPageMain"); //@RequestMapping url
            http.addOrReplace("id", (String) objects[0]);

            HttpClient post = http.create();
            post.request();  // 컨트롤러로 요청

            // 안드로이드에서 응답받음
            String body = post.getBody();
            return body;
        }

        @Override
        protected void onPostExecute(Object o) {  // Object o로 웹데이터가 전달
            Log.d("JSON_RESULT", (String) o);
            Gson gson = new Gson();
            com.example.ex13.dto.Data data = gson.fromJson((String) o, com.example.ex13.dto.Data.class);

            try {
                TextView name = (TextView) findViewById(R.id.tv_name);
                name.setText(data.getData1() + "님");

                Log.d("JSON_RESULT", "이름 = " + data.getMember().get("member_name"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } //onPostExecute
           /* if(((String) o).length() > 0) {
                //JSON 데이터를 Class Object로
                Gson gson = new Gson();
                final Data data = gson.fromJson((String) o, Data.class);

                //회원 이름
                TextView name = findViewById(R.id.edt_name);
                name.setText(data.getData1() + "님");

                //등급
                TextView tvVip = findViewById(R.id.tv_vip);
                String vip = String.valueOf(data.getMember().get("member_cumPoint")); //값이 실수로 넘어옴
                tvVip.setText(getPointVip((int) Double.parseDouble(vip)));

                //건수
                TextView tvMovie = findViewById(R.id.tv_movie);
                TextView tvRest = findViewById(R.id.tv_rest);
                TextView tvPark = findViewById(R.id.tv_park);
                tvMovie.setText(data.getData2() + " 건");
                tvRest.setText(data.getData3() + " 건");
                tvPark.setText(data.getData4() + " 건");

                //이미지 버튼 클릭시
                ImageButton iBtn[] = new ImageButton[3];
                int[] btnRes = {R.id.btn_movie, R.id.btn_rest, R.id.btn_park};
                for (int i = 0; i < btnRes.length; i += 1) {
                    final int idx = i;
                    iBtn[i] = findViewById(btnRes[i]);
                    iBtn[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //페이지 이동 + 값 전달
                            Intent intent = new Intent(MyPageMainActivity.this, MyPageListActivity.class);
                            intent.putExtra("id", id); //로그인 아이디
                            intent.putExtra("idx", String.valueOf(idx)); //무슨 내역인지
                            startActivity(intent);
                        }
                    });
                }

                //회원정보 수정 버튼
                ImageButton iBtnMy = findViewById(R.id.ibtn_my);
                iBtnMy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyPageMainActivity.this, MyPageInfoActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });

                //RecyclerView
                if (data.getBoardList().size() > 0) {
                    RecyclerView recyView = findViewById(R.id.re_board);
                    recyView.setVisibility(View.VISIBLE); //RecyclerView 보이기
                    recyView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    if (adapter == null) {
                        adapter = new MypageRecyAdapter(data.getBoardList());
                        recyView.setAdapter(adapter);
                    }

                    //문의 내역 없음 표시 가리기
                    TextView tvNull = findViewById(R.id.tv_null);
                    tvNull.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "정보 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        }*/
    }//InnerTask
} //MyPageMainActivity
        //포인트에 따른 등급
   /* public String getPointVip(int member_cumPoint) {
        String vip = null;
        if (member_cumPoint <= 15000) {
            vip = "일반";
        } else if (member_cumPoint <= 30000) {
            vip = "VIP";
        } else if (member_cumPoint <= 45000) {
            vip = "VVIP";
        } else {
            vip = "SVIP";
        }
        return vip;
    }*/

