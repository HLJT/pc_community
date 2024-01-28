package com.lt.pc_community.controller;

import com.lt.pc_community.dto.QuestionDTO;
import com.lt.pc_community.mapper.QuestionMapper;
import com.lt.pc_community.mapper.UserMapper;
import com.lt.pc_community.model.Question;
import com.lt.pc_community.model.User;
import com.lt.pc_community.service.QuestionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public String Index(HttpServletRequest request, Model model) {
//        展示登录信息：若未登录，则默认显示登录，若已登录则显示用户名和发布
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
        List<QuestionDTO> questionList =questionService.searchList();
        model.addAttribute("questionList" , questionList);


        return "index";
    }
}
