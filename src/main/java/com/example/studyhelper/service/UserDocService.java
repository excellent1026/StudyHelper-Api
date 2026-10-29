package com.example.studyhelper.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.studyhelper.entity.UserDoc;
import com.example.studyhelper.mapper.UserDocMapper;
import org.springframework.stereotype.Service;

@Service
public class UserDocService extends ServiceImpl<UserDocMapper, UserDoc> {
}