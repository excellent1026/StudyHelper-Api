package com.example.studyhelper.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@TableName("user_doc")
@Schema(description = "用户文档")
public class UserDoc {

    @TableId(type = IdType.AUTO)
    @Schema(description = "文档ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "文档名称")
    private String docName;

    @Schema(description = "WorkBuddy文档ID")
    private String workbuddyDocId;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "上传时间")
    private LocalDateTime uploadTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getDocName() { return docName; }
    public void setDocName(String docName) { this.docName = docName; }
    public String getWorkbuddyDocId() { return workbuddyDocId; }
    public void setWorkbuddyDocId(String workbuddyDocId) { this.workbuddyDocId = workbuddyDocId; }
    public LocalDateTime getUploadTime() { return uploadTime; }
    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }
}
