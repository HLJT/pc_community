package com.lt.pc_community.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Access_Token_DTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;


}
