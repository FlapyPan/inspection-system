package com.inspectionSystem.service;

import com.inspectionSystem.entity.Notice;
import com.inspectionSystem.entity.details.NoticeDetails;

import java.util.List;

public interface NoticeService {
    Notice getNoticeById(Integer id);
    List<Notice> getAllNotices();
    List<Notice> getAllNoticesByPublisherId(Integer id);
    void addNotice(NoticeDetails noticeDetails);
    void updateNotice(Notice notice);
    void removeNotice(Integer id);
}

