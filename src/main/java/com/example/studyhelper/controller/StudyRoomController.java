package com.example.studyhelper.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.studyhelper.common.BusinessException;
import com.example.studyhelper.common.Result;
import com.example.studyhelper.entity.StudyRoom;
import com.example.studyhelper.service.StudyRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studyRoom")
@Tag(name = "自习室管理", description = "自习室增删改查")
public class StudyRoomController {

    @Resource
    private StudyRoomService studyRoomService;

    @GetMapping("/page")
    @Operation(summary = "自习室分页列表")
    public Result<Page<StudyRoom>> page(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Long current,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Long size) {
        return Result.success(studyRoomService.page(new Page<>(current, size)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询自习室")
    public Result<StudyRoom> getById(@PathVariable Long id) {
        return Result.success(studyRoomService.getById(id));
    }

    @PostMapping
    @Operation(summary = "创建自习室")
    public Result<Boolean> add(@RequestBody StudyRoom studyRoom, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        studyRoom.setCreatorId(userId);
        return Result.success(studyRoomService.save(studyRoom));
    }

    @PutMapping
    @Operation(summary = "修改自习室")
    public Result<Boolean> update(@RequestBody StudyRoom studyRoom) {
        return Result.success(studyRoomService.updateById(studyRoom));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除自习室", description = "管理员或创建者权限")
    public Result<Boolean> delete(@PathVariable Long id, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        Long userId = (Long) request.getAttribute("userId");
        StudyRoom room = studyRoomService.getById(id);
        if (room == null) {
            throw new BusinessException("自习室不存在");
        }
        if (!"ADMIN".equals(role) && !room.getCreatorId().equals(userId)) {
            throw new BusinessException(403, "无权删除此自习室");
        }
        return Result.success(studyRoomService.removeById(id));
    }
}
