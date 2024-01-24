package com.lt.pc_community.provider;

import com.alibaba.fastjson.JSON;
import com.lt.pc_community.dto.Access_Token_DTO;
import com.lt.pc_community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
public class GitHubProvider {
//    返回access_token
    public String getAccessToken(Access_Token_DTO accessTokenDto){
//        做post请求，通过传递accesstokenDTO对象来获得accessiontoken
        MediaType mediaType = MediaType.get("application/json");

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(6000, TimeUnit.SECONDS).build();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str=response.body().string();
            String[] split=str.split("&");
            String tokenString=split[0];
            String accessToken = tokenString.split("=")[1];
            System.out.println(str);
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/users/HLJT")
                .header("Accept","application/vnd.github+json")
                .header("Authorization","Bearer "+accessToken)
                .build();
        Response response=client.newCall(request).execute();
        String str=response.body().string();
//        直接用jason去解析字符串
        GitHubUser gitHubUser = JSON.parseObject(str, GitHubUser.class);
        return gitHubUser;

    }

}
