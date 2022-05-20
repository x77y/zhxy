package com.huel.zhxy.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huel.zhxy.pojo.LoginForm;
import com.huel.zhxy.pojo.Student;


public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student getStudentById(Long userId);

    IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student);


//
//    Student getStudentById(Long userId);
//
//    IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student);
}
