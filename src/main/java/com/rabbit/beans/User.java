package com.rabbit.beans;

/**
 * Created by qinxy on 2018/12/10.
 */
public class User {

    private String name;

    private Integer age;

    private String gender;

    public User() {
    }

    public User(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean isMen(){
        if(this.getGender().equals("男")){
            return true;
        }
        return false;
    }
}
