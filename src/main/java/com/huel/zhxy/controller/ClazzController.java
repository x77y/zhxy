package com.huel.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huel.zhxy.pojo.Clazz;
import com.huel.zhxy.pojo.Grade;
import com.huel.zhxy.service.ClazzService;
import com.huel.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sms/clazzController")
@Api(tags = "班级控制器")
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    // sms/clazzController/getClazzsByOpr/1/3?gradeName=&name=

    @ApiOperation("分页带条件查询班级信息")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzByOpr(
            @ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询页大小")@PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页查询的查询条件") Clazz clazz
    ){
        Page<Clazz> page =new Page<>(pageNo,pageSize);

        IPage<Clazz> iPage=clazzService.getClazzsByOpr(page,clazz);

        return Result.ok(iPage);

    }


    @ApiOperation("获取所有班级")
    @GetMapping("/getClazzs")
    public Result getClazzs(){

       List<Clazz>clazzes= clazzService.getClazzs();
       return Result.ok(clazzes);


    }




    @ApiOperation("新增或修改clazz,有id属性是修改,没有则是增加")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(
            @ApiParam("JSON的Clazz对象")@RequestBody Clazz clazz
    ){
        // 接收参数
        // 调用服务层方法完成增减或者修改
        clazzService.saveOrUpdate(clazz);
        return  Result.ok();



    }





    @ApiOperation("删除Clazz信息")
    @DeleteMapping("/deleteClazz")
    public Result  deleteClazz(
            @ApiParam("要删除的所有的Clazz的id的JSON集合")@RequestBody List<Integer> ids
    ){

        clazzService.removeByIds(ids);
        return Result.ok();

    }






}
