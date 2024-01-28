package com.lt.pc_community.mapper;

import com.lt.pc_community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (creator, title, description, gmt_create, gmt_modify,tag) values (#{creator},#{title},#{description},#{gmt_create},#{gmt_modify},#{tag})")
    void insertQuestion(Question question);

    @Select("select * from question")
    List<Question> searchQuestion();

}
