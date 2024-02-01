package com.lt.pc_community.controller;

import com.lt.pc_community.dto.PageDTO;
import com.lt.pc_community.dto.QuestionDTO;
import com.lt.pc_community.mapper.UserMapper;
import com.lt.pc_community.model.User;
import com.lt.pc_community.service.QuestionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public String Index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "6") Integer size) {
        Cookie[] cookies = request.getCookies();
        if(!Objects.isNull(cookies)){
            for(Cookie c:cookies) {
                if(c.getName().equals("token")){
                    String token = c.getValue();
                    User user= userMapper.findToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
//        展示问题列表信息:QuestionDTO 包含了question和user对象
        PageDTO pageDTO =questionService.searchList(page,size);
//        int totalData = pageMapper.countData();
//        int totalPage = (int) Math.ceil((double) totalData/size);
        model.addAttribute("questionList" , pageDTO.getQuestions());
        model.addAttribute("Pages",pageDTO.getPages());
        model.addAttribute("isShowPrevious",pageDTO.isShowPrevious());
        model.addAttribute("isShowFirst",pageDTO.isShowFirst());
        model.addAttribute("isShowNext",pageDTO.isShowNext());
        model.addAttribute("isShowEnd" ,pageDTO.isShowEnd());
        model.addAttribute("curPage" ,pageDTO.getPage());
        model.addAttribute("totalPage" ,pageDTO.getTotalPage());

        return "index";
    }
}
