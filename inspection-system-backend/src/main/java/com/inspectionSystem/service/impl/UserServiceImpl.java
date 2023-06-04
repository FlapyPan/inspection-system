package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.User;
import com.inspectionSystem.entity.details.SalaryDetails;
import com.inspectionSystem.entity.details.UserDetails;
import com.inspectionSystem.entity.resp.Kpi;
import com.inspectionSystem.enums.Role;
import com.inspectionSystem.mapper.SalaryMapper;
import com.inspectionSystem.mapper.UserMapper;
import com.inspectionSystem.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    SalaryMapper salaryMapper;


    @Override
    @Transactional
    public void registerUser(UserDetails userDetails) {
        User user = new User();
        user.setName(userDetails.getName());
        user.setPhone(userDetails.getPhone());
        user.setPassword(userDetails.getPassword());
        userMapper.insertUser(user);
        userMapper.insertRole(user.getId(), Role.STAFF.getValue());
        SalaryDetails salaryDetails = new SalaryDetails();
        salaryDetails.setEmployeeId(String.valueOf(user.getId()));
        salaryDetails.setBasicSalary(3000.0);
        salaryDetails.setKpi(0.0);
        salaryDetails.setKpiCalculatesSalary(0.0);
        salaryMapper.insert(salaryDetails);
    }

    @Override
    public User findUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public void changePassword(Integer id, String newPassword) {
        userMapper.updatePassword(id, newPassword);
    }

    @Transactional
    @Override
    public void removeUserById(Integer id) {
        userMapper.deleteById(id);
        userMapper.deleteRoleByUid(id);
    }

    @Transactional
    @Override
    public void changeUser(User user, Boolean isAdmin) {
        if (isAdmin) {
            userMapper.deleteRoleByUid(user.getId());
            userMapper.changeUser(user);
            List<String> roles = user.getRoles();
            for (String role : roles) {
                userMapper.insertRole(user.getId(), role);
            }
        } else {
            userMapper.updateUser(user);
        }
    }

    @Override
    public Kpi getKpiById(Integer id) {
        return userMapper.getUserKpi(id);
    }

    @Override
    public void updateUserSalary(Integer userId, Double kpiWeight) {
        userMapper.updateSalary(userId, kpiWeight, userMapper.countUnattendedLastMonth(userId));
    }

    @Override
    public Double getLastMonthKpi(Integer userId) {
        return userMapper.selectLastMonthKpi(userId);
    }

    @Override
    public void updateUserKpi(Integer id, Double kpi) {
        userMapper.updateKpi(id, kpi);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userMapper.addUser(user);
        for (String role : user.getRoles()) {
            userMapper.insertRole(user.getId(), role);
        }
        SalaryDetails salaryDetails = new SalaryDetails();
        salaryDetails.setEmployeeId(String.valueOf(user.getId()));
        salaryDetails.setBasicSalary(3000.0);
        salaryDetails.setKpi(0.0);
        salaryDetails.setKpiCalculatesSalary(0.0);
        salaryMapper.insert(salaryDetails);
    }
}
