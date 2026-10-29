package com.example.studyhelper.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.studyhelper.common.BusinessException;
import com.example.studyhelper.common.Result;
import com.example.studyhelper.dto.LoginDTO;
import com.example.studyhelper.dto.RegisterDTO;
import com.example.studyhelper.entity.User;
import com.example.studyhelper.service.UserService;
import com.example.studyhelper.vo.LoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "登录、注册、用户增删改查")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<LoginVO> login(@RequestBody LoginDTO dto) {
        return Result.success(userService.login(dto));
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<LoginVO> register(@RequestBody RegisterDTO dto) {
        return Result.success(userService.register(dto));
    }

    @GetMapping("/page")
    @Operation(summary = "用户分页列表", description = "管理员权限")
    public Result<Page<User>> page(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Long current,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Long size,
            HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new BusinessException(403, "无权限访问");
        }
        return Result.success(userService.page(new Page<>(current, size)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户")
    public Result<User> getById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @PutMapping
    @Operation(summary = "修改用户")
    public Result<Boolean> update(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "管理员权限")
    public Result<Boolean> delete(@PathVariable Long id, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new BusinessException(403, "无权限访问");
        }
        return Result.success(userService.removeById(id));
    }
}
