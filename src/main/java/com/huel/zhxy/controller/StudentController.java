package com.huel.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huel.zhxy.pojo.Student;
import com.huel.zhxy.service.StudentService;
import com.huel.zhxy.util.MD5;
import com.huel.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/sms/studentController")
@Api(tags = "学生控制器")
public class StudentController {


    @Autowired
    private StudentService studentService;

    //GET /sms/studentController/getStudentByOpr/1/3?name=&clazzName=

    //GET /sms/studentController/getStudentByOpr/1/3?name=&clazzName=

    @ApiOperation("分页带条件查询学生信息")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentBuOpr(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询的条件") Student student
    ){
        //分页信息封装Page对象
        Page<Student> pageParam =new Page(pageNo,pageSize);
        // 进行查询
        IPage<Student> studentPage =studentService.getStudentByOpr(pageParam,student);
        // 封装Result返回
        return Result.ok(studentPage);
    }

//    @ApiOperation("分页带条件查询学生信息")
//    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
//    public Result getStudentByOpr(
//            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
//            @ApiParam("页大小") @PathVariable("PageSize") Integer pageSize,
//            @ApiParam("查询的条件")Student student
//            ){
//
////分页信息封装Page对象
//        Page<Student> pageParam = new Page(pageNo, pageSize);
//        // 进行查询
//       IPage<Student>studentIPage= studentService.getStudentByOpr(pageParam,student);
//
//       return Result.ok(studentIPage);
//
//    }


    // POST  http://localhost:9002/sms/studentController/addOrUpdateStudent

    @ApiOperation("保存或者修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(
            @ApiParam("要保存或修改的学生JSON") @RequestBody Student student
    ){
        Integer id = student.getId();
        if (id==null||id==0) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();


    }


    @ApiOperation("学生的单个和多个删除")
    @DeleteMapping("/delStudentById")
    public Result delStudentById(
            @ApiParam("要删除的学生编号的JSON数组") @RequestBody List<Integer> ids){
        studentService.removeByIds( ids);
        return Result.ok();

    }

}
