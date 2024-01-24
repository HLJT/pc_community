package com.lt.pc_community.controller;

import com.lt.pc_community.dto.Access_Token_DTO;
import com.lt.pc_community.dto.GitHubUser;
import com.lt.pc_community.provider.GitHubProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Value("${github.client.secret}")
    private String client_secret;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletRequest request) throws IOException {
        Access_Token_DTO accessTokenDto = new Access_Token_DTO();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setRedirect_uri(redirect_uri);
        accessTokenDto.setClient_secret(client_secret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user);
        if(user!=null){
//            登录成功
            request.getSession().setAttribute("user",user);
            return "redirect:/";

        }
        else{
//            登录失败
            return "redirect:/";
        }
    }
}
