package com.inspectionSystem.service;

import com.inspectionSystem.entity.Attendance;
import com.inspectionSystem.entity.details.AttendanceDetails;

import java.util.List;

public interface AttendanceService {

    Attendance getAttendanceById(Integer id);
    List<Attendance> getAttendanceByUserId(Integer id);
    List<Attendance> getAllAttendances();
    void addAttendance(AttendanceDetails attendanceDetails);
    void updateAttendance(Attendance attendance);
    void removeAttendance(Integer id);

}
