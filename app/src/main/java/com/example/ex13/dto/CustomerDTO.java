package com.example.ex13.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CustomerDTO {

    private String id;
    private String password;
    private String name;
    private Date birthday;
    private String address;
    private String hp;
    private String email;
    private Timestamp regDate;
    // 추가 - 시큐리티
    private String key;	// 이메일인증
    private String authority;  // 권한등급 : ROLE_USER:customer, ROLE_ADMIN:관리자
    private String enabled;  // 계정사용 가능여부(1:사용가능, 0:사용불가) : 이메일인증시 1로 update

    // 디폴트 생성자
    public CustomerDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHp() {
        return hp;
    }
    public void setHp(String hp) {
        this.hp = hp;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "CustomerDTO [id=" + id + ", password=" + password + ", name=" + name + ", birthday=" + birthday
                + ", address=" + address + ", hp=" + hp + ", email=" + email + ", regDate=" + regDate + ", key=" + key
                + ", authority=" + authority + ", enabled=" + enabled + "]";
    }

}
