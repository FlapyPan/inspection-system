package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class PasswordChange {
    private String oldPassword;
    private String newPassword;
}
