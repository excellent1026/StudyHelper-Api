package com.example.studyhelper.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@TableName("study_record")
@Schema(description = "学习记录")
public class StudyRecord {

    @TableId(type = IdType.AUTO)
    @Schema(description = "记录ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "自习室ID")
    private Long roomId;

    @Schema(description = "学习时长(秒)")
    private Integer studyDuration;

    @Schema(description = "学习内容")
    private String studyContent;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    public Integer getStudyDuration() { return studyDuration; }
    public void setStudyDuration(Integer studyDuration) { this.studyDuration = studyDuration; }
    public String getStudyContent() { return studyContent; }
    public void setStudyContent(String studyContent) { this.studyContent = studyContent; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
