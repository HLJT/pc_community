package com.lt.pc_community.mapper;

import com.lt.pc_community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into pc_user (name,account_id,token,gmt_create,gmt_modify,avatar_url) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modify},#{avatar_url})")
    void insertUser(User user);

    @Select("select * from pc_user where token = #{token}")
    User findToken(String token);
    @Select("select * from pc_user where id= #{id}")
    User findById(@Param("id") Integer creator);
}
