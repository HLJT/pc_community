package com.lt.pc_community.controller;

import com.lt.pc_community.dto.Access_Token_DTO;
import com.lt.pc_community.dto.GitHubUser;
import com.lt.pc_community.mapper.UserMapper;
import com.lt.pc_community.model.User;
import com.lt.pc_community.provider.GitHubProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private UserMapper userMapper;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Value("${github.client.secret}")
    private String client_secret;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Access_Token_DTO accessTokenDto = new Access_Token_DTO();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setRedirect_uri(redirect_uri);
        accessTokenDto.setClient_secret(client_secret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if(gitHubUser.getId()!=null){
//            登录成功
            User user=new User();
            user.setName(gitHubUser.getName());
            user.setAccount_id(String.valueOf(gitHubUser.getId()));
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
//            将用户信息持久化到数据库
            userMapper.insertUser(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";

        }
        else{
//            登录失败
            return "redirect:/";
        }
    }
}
