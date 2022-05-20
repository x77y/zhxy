package com.huel.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huel.zhxy.pojo.Teacher;
import com.huel.zhxy.service.TeacherService;
import com.huel.zhxy.util.MD5;
import com.huel.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/sms/teacherController")
@Api(tags = "教师控制器")
public class TeacherController {

@Autowired
private TeacherService teacherService;
//    Request URL: http://localhost:9001/sms/teacherController/getTeachers/1/3
//    Request Method: GET

    @ApiOperation("分页带条件查询教师信息")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件")  Teacher teacher
    ){
        Page<Teacher> pageParam = new Page<>(pageNo, pageSize);
       IPage<Teacher> iPage=teacherService.getTeacherByOpr(pageParam,teacher);

        return Result.ok(iPage);


    }

//    @ApiOperation("分页获取教师信息,带搜索条件")
//    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
//    public Result getTeachers(
//            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo ,
//            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize ,
//            @ApiParam("查询条件") Teacher teacher
//    ){
//        Page<Teacher> paraParam =new Page<>(pageNo,pageSize);
//
//        IPage<Teacher> page = teacherService.getTeacherByOpr(paraParam,teacher);
//
//        return Result.ok(page);
//    }

    @ApiOperation("删除教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(
            @ApiParam("要删除的管理员的多个ID的JSON") @RequestBody ArrayList<Integer> ids
            )
    {
        teacherService.removeByIds(ids);
        return Result.ok();
    }

//    Request URL: http://localhost:9001/sms/teacherController/saveOrUpdateTeacher
//    Request Method: POST
    @ApiOperation("保存或修改教师信息")
    @RequestMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(
            @ApiParam("JSON的Teacher对象")@RequestBody Teacher teacher
    ){
        Integer id = teacher.getId();
        if (id==null||id==0) {
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }

        teacherService.saveOrUpdate(teacher);
        return Result.ok();

    }







}
