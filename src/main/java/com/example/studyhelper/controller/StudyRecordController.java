package com.example.studyhelper.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.studyhelper.common.Result;
import com.example.studyhelper.entity.StudyRecord;
import com.example.studyhelper.service.StudyRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studyRecord")
@Tag(name = "学习记录", description = "学习记录增删改查")
public class StudyRecordController {

    @Resource
    private StudyRecordService studyRecordService;

    @GetMapping("/page")
    @Operation(summary = "学习记录分页列表")
    public Result<Page<StudyRecord>> page(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Long current,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Long size,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        String role = (String) request.getAttribute("role");
        LambdaQueryWrapper<StudyRecord> wrapper = new LambdaQueryWrapper<>();
        if (!"ADMIN".equals(role)) {
            wrapper.eq(StudyRecord::getUserId, userId);
        }
        return Result.success(studyRecordService.page(new Page<>(current, size), wrapper));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询学习记录")
    public Result<StudyRecord> getById(@PathVariable Long id) {
        return Result.success(studyRecordService.getById(id));
    }

    @PostMapping
    @Operation(summary = "提交学习记录")
    public Result<Boolean> add(@RequestBody StudyRecord studyRecord, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        studyRecord.setUserId(userId);
        return Result.success(studyRecordService.save(studyRecord));
    }

    @PutMapping
    @Operation(summary = "修改学习记录")
    public Result<Boolean> update(@RequestBody StudyRecord studyRecord) {
        return Result.success(studyRecordService.updateById(studyRecord));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除学习记录")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(studyRecordService.removeById(id));
    }
}
