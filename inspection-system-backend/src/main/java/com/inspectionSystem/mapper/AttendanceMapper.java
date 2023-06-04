package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Attendance;
import com.inspectionSystem.entity.details.AttendanceDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    @Insert("INSERT INTO attendance(checkin_time, closing_time, create_time, is_ask_for_leave, ask_for_leave_reason, other, employee_id) VALUES(#{checkinTime}, #{closingTime}, NOW(), #{isAskForLeave}, #{askForLeaveReason}, #{other},#{employeeId})")
    void insertAttendance(AttendanceDetails attendanceDetails);

    @Delete("DELETE FROM attendance WHERE id = #{id}")
    void deleteAttendanceById(Integer id);

    @Update("UPDATE attendance SET checkin_time=#{checkinTime}, closing_time=#{closingTime}, is_ask_for_leave=#{isAskForLeave}, not_sign_in_times=#{notSignInTimes}, ask_for_leave_reason=#{askForLeaveReason}, other=#{other}, employee_id = #{employeeId} WHERE id=#{id}")
    void updateAttendance(Attendance attendance);

    @Select("SELECT * FROM attendance WHERE id = #{id}")
    Attendance selectAttendanceById(Integer id);

    @Select("SELECT * FROM attendance WHERE employee_id = #{id}")
    List<Attendance> selectAttendanceByUserId(Integer id);

    @Select("SELECT * FROM attendance")
    List<Attendance> selectAllAttendances();

}
