package com.springdemo.demo.mapper;

import com.springdemo.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id,token, gmt_create, gmt_modified, avatar_url) values (#{name}, " +
            "#{accountId},#{token}, #{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{creator}")
    User findById(Integer creator);

    @Update("update user set name=#{name},token=#{token},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified}," +
            "bio=#{bio},avatar_url=#{avatarUrl} where account_id = #{accountId}")
    void update(User user);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);
}
