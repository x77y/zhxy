package com.huel.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huel.zhxy.pojo.Admin;
import com.huel.zhxy.service.AdminService;
import com.huel.zhxy.util.CreateVerifiCodeImage;
import com.huel.zhxy.util.MD5;
import com.huel.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/sms/adminController")
@Api(tags = "管理员控制器")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // GET
    //	http://localhost:9002/sms/adminController/getAllAdmin/1/3?    adminName=a
    @ApiOperation("分页带条件查询管理员信息")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("管理员名字") String adminName
    ){
        Page<Admin> pageParam =new Page<Admin>(pageNo,pageSize);

        IPage<Admin> iPage=adminService.getAdminsByOpr(pageParam,adminName);
        return Result.ok(iPage);
    }

//   http://localhost:9001/sms/adminController/saveOrUpdateAdmin
   // Request Method: POST

    @ApiOperation("保存或修改管理员信息")
    @PostMapping("saveOrUpdateAdmin")

    public Result saveOrUpdateAdmin(
            @ApiParam("JSON的Admin对象") @RequestBody Admin admin
    )
    {
        Integer id = admin.getId();
        if (id==null||id==0) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        // 接收参数
        // 调用服务层方法完成增减或者修改
        adminService.saveOrUpdate(admin);
        return Result.ok();


    }

//    Request URL: http://localhost:9001/sms/adminController/deleteAdmin
//    Request Method: DELETE
//    @ApiOperation("删除管理员信息")
//    @DeleteMapping("/deleteAdmin")
//    public Result deleteAdmin(
//            @ApiParam("要删除的所有的Admin的id的JSON集合") @RequestBody List<Integer> ids
//    ){
//
//    }

    @ApiOperation("删除单个或者多个管理员信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(
            @ApiParam("要删除的管理员的多个ID的JSON集合") @RequestBody ArrayList<Integer>ids
            ){
        adminService.removeByIds(ids);
        return Result.ok();
    }

//    Request URL: http://localhost:9001/sms/teacherController/deleteTeacher
//    Request Method: DELETE





}
