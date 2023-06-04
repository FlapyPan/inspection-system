package com.inspectionSystem.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RestBean<T> {

    Integer code;
    String msg;
    T data;

    public RestBean(HttpStatus status, String msg, T data) {
        this.code = status.value();
        this.msg = msg;
        this.data = data;
    }
    public RestBean(HttpStatus status, String msg) {
        this.code = status.value();
        this.msg = msg;
    }
}
