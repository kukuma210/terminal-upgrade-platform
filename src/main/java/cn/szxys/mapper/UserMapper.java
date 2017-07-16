package cn.szxys.mapper;

import cn.szxys.Entity.UserEntity;
import cn.szxys.pub.UserSexEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Shinewa on 2017/7/15.
 */
public interface UserMapper {
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "UserSex", javaType = UserSexEnum.class),
            @Result(property = "userName", column = "UserName")
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE ID = #{id}")
    @Results({
            @Result(property = "userSex",  column = "UserSex", javaType = UserSexEnum.class),
            @Result(property = "userName", column = "UserName")
    })
    UserEntity getOne(Long id);

    @Insert("INSERT INTO users(UserName,PassWord,UserSex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(UserEntity user);

    @Update("UPDATE users SET UserName=#{userName},UserSex=#{userSex},Password=#{passWord} WHERE ID =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE ID =#{id}")
    void delete(Long id);
}
