package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Notice;
import com.inspectionSystem.entity.details.NoticeDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class NoticeController {

    @Resource
    NoticeService service;

    @GetMapping("/broadcast")//获取公告列表
    public RestBean<?> noticeList(){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAllNotices());
    }

    @GetMapping("/broadcast/{id}")//获取公告
    public RestBean<?> getNotice(@PathVariable Integer id){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getNoticeById(id));
    }


    @PostMapping("/broadcast/{id}")//修改公告
    public RestBean<?> change(@RequestBody NoticeDetails noticeDetails, @PathVariable Integer id){
        Notice notice = new Notice();
        notice.setId(id);
        notice.setContent(noticeDetails.getContent());
        notice.setTitle(noticeDetails.getTitle());
        notice.setPublisherId(noticeDetails.getPublisherId());
        service.updateNotice(notice);
        return new RestBean<Void>(HttpStatus.OK,"修改成功！");
    }


    @PutMapping("/broadcast")//添加公告
    public RestBean<?> addNotice(@RequestBody NoticeDetails notice){
        service.addNotice(notice);
        return new RestBean<Void>(HttpStatus.OK,"添加成功！");
    }


    @DeleteMapping("/broadcast/{id}")//删除公告
    public RestBean<?> remove(@PathVariable Integer id){
        service.removeNotice(id);
        return new RestBean<Void>(HttpStatus.OK,"删除成功！");
    }

}
