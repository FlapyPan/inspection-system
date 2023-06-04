package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.User;
import com.inspectionSystem.entity.resp.Kpi;
import com.inspectionSystem.entity.resp.KpiStruct;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(name, password, phone, create_time) VALUES(#{name}, #{password}, #{phone}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Insert("INSERT INTO role(uid, role, create_time) VALUES(#{uid}, #{role}, NOW())")
    void insertRole(Integer uid, String role);

    @Select("SELECT role FROM role WHERE uid = #{id}")
    List<String> selectRolesByUserId(Integer id);

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.inspectionSystem.mapper.UserMapper.selectRolesByUserId")
            )
    }
    )
    User selectUserByPhone(String phone);

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.inspectionSystem.mapper.UserMapper.selectRolesByUserId")
            )
    }
    )
    User selectUserById(Integer id);


    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.inspectionSystem.mapper.UserMapper.selectRolesByUserId")
            )
    }
    )
    List<User> selectAllUsers();


    @Update("UPDATE user SET password = #{newPassword} WHERE id = #{id}")
    void updatePassword(Integer id, String newPassword);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM role WHERE uid = #{id}")
    void deleteRoleByUid(Integer uid);


    @Update("UPDATE user SET phone = #{phone}, position = #{position}, department = #{department} WHERE id = #{id}")
    void updateUser(User user);

    @Update("UPDATE user SET name = #{name}, password = #{password}, gender = #{gender}, " +
            "id_card = #{idCard}, email = #{email}, phone = #{phone}, birth_date = #{birthDate}, " +
            "position = #{position}, department = #{department} WHERE id = #{id}")
    void changeUser(User user);

    @Select("SELECT SUM(score) AS kpi, #{id} as id FROM (\n" +
            "  SELECT employee_id, SUM(score) AS score FROM thesis WHERE MONTH(publish_time) = MONTH(CURRENT_DATE()) AND YEAR(publish_time) = YEAR(CURRENT_DATE()) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM book WHERE MONTH(publish_time) = MONTH(CURRENT_DATE()) AND YEAR(publish_time) = YEAR(CURRENT_DATE()) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM patent WHERE MONTH(application_date) = MONTH(CURRENT_DATE()) AND YEAR(application_date) = YEAR(CURRENT_DATE()) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM software_work WHERE MONTH(publication_date) = MONTH(CURRENT_DATE()) AND YEAR(publication_date) = YEAR(CURRENT_DATE()) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM contracts WHERE MONTH(create_time) = MONTH(CURRENT_DATE()) AND YEAR(create_time) = YEAR(CURRENT_DATE()) GROUP BY employee_id\n" +
            ") t \n" +
            "JOIN user u ON t.employee_id = u.id \n" +
            "WHERE u.id = #{id}")
    @Results({
            @Result(property = "kpi", column = "kpi"),
            @Result(property = "kpiStructs", column = "id", javaType = List.class,
                    many = @Many(select = "com.inspectionSystem.mapper.UserMapper.getKpiStructs"))
    })
    Kpi getUserKpi(Integer id);


    @Select("SELECT SUM(score) AS kpi FROM (\n" +
            "  SELECT employee_id, SUM(score) AS score FROM thesis WHERE MONTH(publish_time) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(publish_time) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM book WHERE MONTH(publish_time) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(publish_time) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM patent WHERE MONTH(application_date) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(application_date) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM software_work WHERE MONTH(publication_date) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(publication_date) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH) GROUP BY employee_id\n" +
            "  UNION ALL \n" +
            "  SELECT employee_id, SUM(score) AS score FROM contracts WHERE MONTH(create_time) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(create_time) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH) GROUP BY employee_id\n" +
            ") t \n" +
            "JOIN user u ON t.employee_id = u.id \n" +
            "WHERE u.id = #{id}")
    Double selectLastMonthKpi(Integer id);

    @Select("SELECT 'thesis' as name, SUM(score) AS value FROM thesis WHERE employee_id = #{id} AND MONTH(publish_time) = MONTH(CURRENT_DATE()) AND YEAR(publish_time) = YEAR(CURRENT_DATE()) AND approval_status = '1' GROUP BY employee_id " +
            "UNION " +
            "SELECT 'book' as name, SUM(score) AS value FROM book WHERE employee_id = #{id} AND MONTH(publish_time) = MONTH(CURRENT_DATE()) AND YEAR(publish_time) = YEAR(CURRENT_DATE()) AND approval_status = '1' GROUP BY employee_id " +
            "UNION " +
            "SELECT 'patent' as name, SUM(score) AS value FROM patent WHERE employee_id = #{id} AND MONTH(application_date) = MONTH(CURRENT_DATE()) AND YEAR(application_date) = YEAR(CURRENT_DATE()) AND approval_status = '1' GROUP BY employee_id " +
            "UNION " +
            "SELECT 'softwareWork' as name, SUM(score) AS value FROM software_work WHERE employee_id = #{id} AND MONTH(publication_date) = MONTH(CURRENT_DATE()) AND YEAR(publication_date) = YEAR(CURRENT_DATE()) AND approval_status = '1' GROUP BY employee_id " +
            "UNION " +
            "SELECT'contracts' as name, SUM(score) AS value FROM contracts WHERE employee_id = #{id} AND MONTH(create_time) = MONTH(CURRENT_DATE()) AND YEAR(create_time) = YEAR(CURRENT_DATE()) AND approval_status = '1' GROUP BY employee_id"
    )
    List<KpiStruct> getKpiStructs(Integer id);

    @Select("SELECT COUNT(*) FROM attendance WHERE employee_id = #{id} AND YEAR(create_time) = YEAR(CURDATE()) " +
            "AND MONTH(create_time) = MONTH(CURDATE()) " +
            "AND (checkin_time IS NULL OR (is_ask_for_leave = '0'))")
    Integer countUnattended(Integer id);

    @Select("SELECT COUNT(*) FROM attendance WHERE employee_id = #{id} AND YEAR(create_time) = YEAR(CURDATE() - INTERVAL 1 MONTH) \n" +
            "AND MONTH(create_time) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND (checkin_time IS NULL OR (is_ask_for_leave = '0'))")
    Integer countUnattendedLastMonth(Integer id);


    @Update("UPDATE salary SET kpi = #{kpi} WHERE employee_id = #{userId}")
    void updateKpi(Integer userId, Double kpi);

    @Update("UPDATE salary SET kpi_calculates_salary = #{kpiWeight} * kpi + basic_salary - 200 * #{unattendedCount} WHERE employee_id = #{userId}")
    void updateSalary(Integer userId, Double kpiWeight, Integer unattendedCount);


    @Insert("INSERT user (name, password, gender, id_card, email, phone, birth_date, position, department, create_time) " +
            "VALUES (#{name}, #{password}, #{gender}, #{idCard}, #{email}, #{phone}, #{birthDate}, #{position}, #{department}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addUser(User user);
}
