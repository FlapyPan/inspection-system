package com.inspectionSystem.service;

import com.inspectionSystem.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class ScheduledService {

    @Resource
    UserService service;

    final static Double kpiWeight = 100.0;

    @Scheduled(cron = "0 0 10 * * ?")
    @Transactional
    public void updateSalary() {
        List<User> userList = service.getAllUsers();
        for (User user : userList) {
            Integer userId = user.getId();
            service.updateUserKpi(userId, service.getLastMonthKpi(userId));
            service.updateUserSalary(userId, kpiWeight);
            log.info("职工：{}更新上月薪资成功！",user.getName());
        }
    }

}
