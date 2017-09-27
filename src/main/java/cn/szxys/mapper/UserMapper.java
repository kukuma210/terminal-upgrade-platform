package cn.szxys.mapper;

import cn.szxys.Entity.UserEntity;
import cn.szxys.pub.UserSexEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Shinewa on 2017/7/15.
 * 强烈建议数据库表字段名和对应的实体属性名一模一样，会节省
 * 大量工作，这就要求数据库表的设计者的字段要以小写开头
 */
public interface UserMapper {
    @Select("SELECT * FROM users")
    @Results
    List<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE ID = #{id}")
    UserEntity getOne(Long id);

    @Insert("INSERT INTO users(Name,PassWord,UserSex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(UserEntity user);

    @Update("UPDATE users SET Name=#{userName},UserSex=#{userSex},Password=#{passWord} WHERE ID =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE ID =#{id}")
    void delete(Long id);
}
