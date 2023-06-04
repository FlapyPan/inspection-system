package com.inspectionSystem.entity;

import lombok.Data;

@Data
public class Notice {
    Integer id;
    String title;
    String content;
    String publishDate;
    Integer publisherId;
}
