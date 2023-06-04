package com.inspectionSystem.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {

    Integer id;

    String name;

    String password;

    String gender;

    String idCard;

    String email;

    String phone;

    String birthDate;

    String position;

    String department;

    String createTime;

    List<String> roles;


}
