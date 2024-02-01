package com.lt.pc_community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageDTO {
//    承载分页组件
    private List<QuestionDTO> questions;
    private Integer totalData;
    private Integer totalPage;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showNext;
    private boolean showEnd;

    private final int pageSize=5;

    private Integer page;

    private List<Integer> pages=new ArrayList<>();

    public void setPagination(Integer totalData, Integer page, Integer size) {
        this.page=page;
        this.totalPage = (int) Math.ceil((double)totalData/size);
//        是否展示上下页：第一页和最后一页不展示
        if(page == 1){
            showPrevious =false;
            showNext=true;
        }
        else if(page == totalPage){
            showPrevious =true;
            showNext=false;
        }
        else{
            showPrevious =true;
            showNext=true;
        }

//控制当前页之前
        if(page>2){
            pages.add(page-2);
            pages.add(page-1);
            pages.add(page);
        }
        else if(page==2){
            pages.add(page-1);
            pages.add(page);
        }
        else{
            pages.add(1);
        }
//控制当前页之后
        if(page<=totalPage-2){
            pages.add(page+1);
            pages.add(page+2);
        }
        if(page ==totalPage-1){
            pages.add(page+1);
        }

        //       是否展示置顶置地页，列表包含第一页或者最后一页时不展示
        if(pages.contains(1)){
            showFirst=false;
            showEnd=true;
        }
        else if(pages.contains(totalPage)){
            showFirst=true;
            showEnd=false;
        }
        else{
            showFirst=true;
            showEnd=true;
        }
    }
}
