package com.lt.pc_community.dto;

import com.lt.pc_community.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDTO {
    private User user;
    private Integer id;
    private Integer creator;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modify;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
}
