package com.example.ex13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * Created by psn on 2018-01-23.
 */
public class Web {

    // 1. 안드로이드 애뮬레이터 실행

    // 2. 스프링 연동 - 크롬 실행 : http://localhost/gsonEx/


    // cmd 창 > ipconfig 확인 .. 연결이 끊김 으로 되어있으면 연결불가
    public static String ip = "192.168.0.89"; //본인 IP
    // http://본인IP:8081/컨텍스트명(스프링 3번째 패키지)/   ==> 현재 포트번호가 80으로 설정했으므로 포트번호 생략
    public static String servletURL = "http://" + ip + "/gsonEx/android/"; //연결할 JSP URL

    // id, password 입력후 로그인 클릭 => 스프링으로 연동된다.

}
