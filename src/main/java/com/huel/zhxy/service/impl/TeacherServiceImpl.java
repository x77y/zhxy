package com.huel.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huel.zhxy.mapper.TeacherMapper;
import com.huel.zhxy.pojo.LoginForm;
import com.huel.zhxy.pojo.Student;
import com.huel.zhxy.pojo.Teacher;
import com.huel.zhxy.service.TeacherService;
import com.huel.zhxy.util.MD5;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("teaService")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>implements TeacherService {

    @Override
    public Teacher login(LoginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Teacher teacher = baseMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public Teacher getByTeacherById(Long userId) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("id",userId);

        return baseMapper.selectOne(teacherQueryWrapper)   ;
    }

    @Override
    public IPage<Teacher> getTeacherByOpr(Page<Teacher> pageParam, Teacher teacher) {

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isNullOrEmpty(teacher.getName())){

            teacherQueryWrapper.like("name",teacher.getName());

        }

        if (!StringUtils.isNullOrEmpty(teacher.getClazzName())){

            teacherQueryWrapper.like("clazz_name",teacher.getClazzName());

        }
        Page<Teacher> teacherPage = baseMapper.selectPage(pageParam, teacherQueryWrapper);

        return teacherPage;

    }
}
