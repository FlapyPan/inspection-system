package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Book;
import com.inspectionSystem.entity.details.BookDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Insert("INSERT INTO book(type, name, approval_status, score, isbn, intellectual_property, research_dept, first_discipline, language, publisher_level, publisher_unit, publish_time, discipline_category, project_source, school_signature, publish_location, participants_counts, total_words, first_author_name, first_author_employee_id, first_author_type,employee_id) VALUES(#{type}, #{name}, #{approvalStatus}, #{score}, #{isbn}, #{intellectualProperty}, #{researchDept}, #{firstDiscipline}, #{language}, #{publisherLevel}, #{publisherUnit}, #{publishTime}, #{disciplineCategory}, #{projectSource}, #{schoolSignature}, #{publishLocation}, #{participantsCounts}, #{totalWords}, #{firstAuthorName}, #{firstAuthorEmployeeId}, #{firstAuthorType},#{employeeId})")
    void addBook(BookDetails book);

    @Delete("DELETE FROM book WHERE id=#{id}")
    void deleteBook(Integer id);

    @Update("UPDATE book SET type=#{type}, name=#{name}, approval_status=#{approvalStatus}, score=#{score}, isbn=#{isbn}, intellectual_property=#{intellectualProperty}, research_dept=#{researchDept}, first_discipline=#{firstDiscipline}, language=#{language}, publisher_level=#{publisherLevel}, publisher_unit=#{publisherUnit}, publish_time=#{publishTime}, discipline_category=#{disciplineCategory}, project_source=#{projectSource}, school_signature=#{schoolSignature}, publish_location=#{publishLocation}, participants_counts=#{participantsCounts}, total_words=#{totalWords}, first_author_name=#{firstAuthorName}, first_author_employee_id=#{firstAuthorEmployeeId}, first_author_type=#{firstAuthorType} ,employee_id = #{employeeId} WHERE id=#{id}")
    void updateBook(Book book);

    @Select("SELECT * FROM book WHERE id=#{id}")
    Book selectBookById(Integer id);

    @Select("SELECT * FROM book")
    List<Book> selectAll();

    @Select("SELECT * FROM book WHERE employee_id = #{employeeId}")
    List<Book> selectBooksByEmployeeId(Integer employeeId);
}
