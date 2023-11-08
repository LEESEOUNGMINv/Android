package com.example.ex13.dto;



import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.SerializedName;  // import후 오른쪽 위 sync now를 클릭해야 동기화됨


/**
 * Created by psn on 2018-02-07.
 */
/* 스프링 컨트롤러와 JSON 연동을 위해 Map<키,값>으로 받기위한 클래스 */
public class Data {
    // 스프링에서 값 넘길 때 사용한 키(그대로 받아야하니 일치해야 함
    // 더 추가해도 되지만 스프링 컨트롤러에서도 같이 만들어야 함.
    // 사용하지 않을 값은 0으로 초기화하면 됨
    private String data1;
    private String data2;
    private String data3;
    private String data4;

    @SerializedName("customer")   /*import후 오른쪽 위 sync now를 클릭해야 동기화됨*/
    private Map<String, String> member = new HashMap<String, String>();
    //getter,setter
    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setAuth(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data13) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public Map<String, String> getMember() {
        return member;
    }

    public void setMember(Map<String, String> member) {
        this.member = member;
    }

}
