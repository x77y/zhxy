package com.huel.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huel.zhxy.pojo.LoginForm;
import com.huel.zhxy.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {
    Teacher login(LoginForm loginForm);

    Teacher getByTeacherById(Long userId);

    IPage<Teacher> getTeacherByOpr(Page<Teacher> pageParam, Teacher teacher);


//    Teacher login(LoginForm loginForm);
//
//
//    Teacher getByTeacherById(Long userId);
//
//    IPage<Teacher> getTeachersByOpr(Page<Teacher> paraParam, Teacher teacher);
}
