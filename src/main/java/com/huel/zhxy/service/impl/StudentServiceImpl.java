package com.huel.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huel.zhxy.mapper.StudentMapper;
import com.huel.zhxy.pojo.Admin;
import com.huel.zhxy.pojo.LoginForm;
import com.huel.zhxy.pojo.Student;
import com.huel.zhxy.service.StudentService;
import com.huel.zhxy.util.MD5;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("stuService")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>implements StudentService {

    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));

        Student student = baseMapper.selectOne(queryWrapper);


        return student;
    }

    @Override
    public Student getStudentById(Long userId) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("id",userId);
        return baseMapper.selectOne(studentQueryWrapper);
    }

    @Override
    public IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student) {

        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isNullOrEmpty(student.getName())){
            studentQueryWrapper.like("name",student.getName());
        }
        if (!StringUtils.isNullOrEmpty(student.getClazzName())){

            studentQueryWrapper.like("clazz_name",student.getClazzName());

        }

        studentQueryWrapper.orderByDesc("id");
        Page<Student> studentPage = baseMapper.selectPage(pageParam, studentQueryWrapper);
        return studentPage;


    }
}
