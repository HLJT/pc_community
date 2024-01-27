package com.lt.pc_community.controller;


import com.lt.pc_community.mapper.QuestionMapper;
import com.lt.pc_community.mapper.UserMapper;
import com.lt.pc_community.model.Question;
import com.lt.pc_community.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class publishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
        
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){
        if(title==null ||title==""){
            model.addAttribute("Error","标题不能为空！");
            return "publish";
        }
        if(description==null ||description==""){
            model.addAttribute("Error","问题描述不能为空！");
            return "publish";
        }
        Question question = new Question();
        Cookie[] cookies = request.getCookies();
        User user = new User();
        if(!Objects.isNull(cookies)){
            for(Cookie c:cookies) {
                if(c.getName().equals("token")){
                    String token = c.getValue();
                    user= userMapper.findToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if(Objects.isNull(cookies) || user==null){
            model.addAttribute("Error","用户尚未登陆");
            return "publish";
        }
        question.setCreator(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        questionMapper.insertQuestion(question);
        return "redirect:/";
    }
}
