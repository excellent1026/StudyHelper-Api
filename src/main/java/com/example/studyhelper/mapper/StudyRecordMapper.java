package com.example.studyhelper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studyhelper.entity.StudyRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyRecordMapper extends BaseMapper<StudyRecord> {
}