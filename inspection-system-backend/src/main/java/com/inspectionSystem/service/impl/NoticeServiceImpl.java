package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Notice;
import com.inspectionSystem.entity.details.NoticeDetails;
import com.inspectionSystem.mapper.NoticeMapper;
import com.inspectionSystem.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeMapper mapper;

    @Override
    public Notice getNoticeById(Integer id) {
        return mapper.selectNoticeById(id);
    }

    @Override
    public List<Notice> getAllNotices() {
        return mapper.selectAllNotices();
    }

    @Override
    public List<Notice> getAllNoticesByPublisherId(Integer id) {
        return mapper.selectNoticeByPublisherId(id);
    }

    @Override
    public void addNotice(NoticeDetails noticeDetails) {
        mapper.insertNotice(noticeDetails);
    }

    @Override
    public void updateNotice(Notice notice) {
        mapper.updateNotice(notice);
    }

    @Override
    public void removeNotice(Integer id) {
        mapper.deleteNotice(id);
    }
}
