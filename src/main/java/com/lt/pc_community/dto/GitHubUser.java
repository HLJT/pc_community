package com.lt.pc_community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GitHubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatar_url;
}
