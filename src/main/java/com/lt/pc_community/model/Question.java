package com.lt.pc_community.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
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
