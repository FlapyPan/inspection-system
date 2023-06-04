package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class AttendanceDetails {
    String checkinTime;
    String closingTime;
    Boolean isAskForLeave;
    String askForLeaveReason;
    String other;
    String employeeId;
}
