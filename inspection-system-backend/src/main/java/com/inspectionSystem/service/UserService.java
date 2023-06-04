package com.inspectionSystem.service;

import com.inspectionSystem.entity.User;
import com.inspectionSystem.entity.details.UserDetails;
import com.inspectionSystem.entity.resp.Kpi;

import java.util.List;

public interface UserService {

    void registerUser(UserDetails user);

    User findUserByPhone(String phone);
    User findUserById(Integer id);

    List<User> getAllUsers();

    void changePassword(Integer id, String newPassword);

    void removeUserById(Integer id);

    void changeUser(User user, Boolean isAdmin);

    Kpi getKpiById(Integer id);

    void updateUserSalary(Integer userId,Double kpiWeight);

    Double getLastMonthKpi(Integer userId);

    void updateUserKpi(Integer id, Double kpi);

    void addUser(User user);
}
