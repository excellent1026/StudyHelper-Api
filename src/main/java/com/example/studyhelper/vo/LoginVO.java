package com.example.studyhelper.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "登录返回")
public class LoginVO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "JWT Token")
    private String token;

    public LoginVO() {}

    public LoginVO(Long userId, String username, String nickname, String role, String token) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.role = role;
        this.token = token;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
