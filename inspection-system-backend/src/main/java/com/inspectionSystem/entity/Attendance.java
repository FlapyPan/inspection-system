package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.AttendanceDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attendance {
    Integer id;
    String checkinTime;
    String closingTime;
    String createTime;
    Boolean isAskForLeave;
    String askForLeaveReason;
    String other;
    String employeeId;

    public Attendance(AttendanceDetails details) {
        checkinTime = details.getCheckinTime();
        closingTime = details.getClosingTime();
        isAskForLeave = details.getIsAskForLeave();
        askForLeaveReason = details.getAskForLeaveReason();
        other = details.getOther();
        employeeId = details.getEmployeeId();
    }


}
