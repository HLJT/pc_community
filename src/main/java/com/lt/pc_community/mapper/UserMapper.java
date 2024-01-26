package com.lt.pc_community.mapper;

import com.lt.pc_community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into pc_user (name,account_id,token,gmt_create,gmt_modify) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modify})")
    void insertUser(User user);
}
