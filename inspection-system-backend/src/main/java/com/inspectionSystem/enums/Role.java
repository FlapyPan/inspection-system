package com.inspectionSystem.enums;

public enum Role {

    STAFF("staff"),

    AUDITOR("auditor"),

    ADMIN("admin");


    private final String value;


    Role(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
