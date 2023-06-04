package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Notice;
import com.inspectionSystem.entity.details.NoticeDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Insert("INSERT INTO notice(title, content, publish_date, publisher_id) VALUES(#{title}, #{content}, DATE_FORMAT(NOW(), '%Y-%m-%d'), #{publisherId})")
    int insertNotice(NoticeDetails noticeDetails);

    @Delete("DELETE FROM notice WHERE id=#{id}")
    void deleteNotice(Integer id);

    @Update("UPDATE notice SET title=#{title}, content=#{content}, publisher_id=#{publisherId} WHERE id=#{id}")
    void updateNotice(Notice notice);

    @Select("SELECT * FROM notice WHERE id=#{id}")
    Notice selectNoticeById(Integer id);

    @Select("SELECT * FROM notice WHERE publisher_id=#{publisherId}")
    List<Notice> selectNoticeByPublisherId(Integer publisherId);


    @Select("SELECT * FROM notice")
    List<Notice> selectAllNotices();


}
