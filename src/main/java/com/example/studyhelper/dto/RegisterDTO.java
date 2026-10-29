package com.example.studyhelper.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "注册请求")
public class RegisterDTO {

    @Schema(description = "用户名", example = "newuser")
    private String username;

    @Schema(description = "密码", example = "123456")
    private String password;

    @Schema(description = "昵称", example = "新用户")
    private String nickname;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
