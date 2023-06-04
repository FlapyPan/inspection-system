package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Attendance;
import com.inspectionSystem.entity.details.AttendanceDetails;
import com.inspectionSystem.mapper.AttendanceMapper;
import com.inspectionSystem.service.AttendanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Resource
    AttendanceMapper mapper;

    @Override
    public Attendance getAttendanceById(Integer id) {
        return mapper.selectAttendanceById(id);
    }

    @Override
    public List<Attendance> getAttendanceByUserId(Integer id) {
        return mapper.selectAttendanceByUserId(id);
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return mapper.selectAllAttendances();
    }

    @Override
    public void addAttendance(AttendanceDetails attendanceDetails) {
        mapper.insertAttendance(attendanceDetails);
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        mapper.updateAttendance(attendance);
    }

    @Override
    public void removeAttendance(Integer id) {
        mapper.deleteAttendanceById(id);
    }
}
