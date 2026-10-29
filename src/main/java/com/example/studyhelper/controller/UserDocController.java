package com.example.studyhelper.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.studyhelper.common.Result;
import com.example.studyhelper.entity.UserDoc;
import com.example.studyhelper.service.UserDocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userDoc")
@Tag(name = "用户文档", description = "用户文档增删改查")
public class UserDocController {

    @Resource
    private UserDocService userDocService;

    @GetMapping("/page")
    @Operation(summary = "用户文档分页列表")
    public Result<Page<UserDoc>> page(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Long current,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Long size,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        String role = (String) request.getAttribute("role");
        LambdaQueryWrapper<UserDoc> wrapper = new LambdaQueryWrapper<>();
        if (!"ADMIN".equals(role)) {
            wrapper.eq(UserDoc::getUserId, userId);
        }
        return Result.success(userDocService.page(new Page<>(current, size), wrapper));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询文档")
    public Result<UserDoc> getById(@PathVariable Long id) {
        return Result.success(userDocService.getById(id));
    }

    @PostMapping
    @Operation(summary = "上传文档")
    public Result<Boolean> add(@RequestBody UserDoc userDoc, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        userDoc.setUserId(userId);
        return Result.success(userDocService.save(userDoc));
    }

    @PutMapping
    @Operation(summary = "修改文档信息")
    public Result<Boolean> update(@RequestBody UserDoc userDoc) {
        return Result.success(userDocService.updateById(userDoc));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除文档")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(userDocService.removeById(id));
    }
}
