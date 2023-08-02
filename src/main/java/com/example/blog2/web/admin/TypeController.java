package com.example.blog2.web.admin;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.Type;
import com.example.blog2.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@CrossOrigin
@Api(tags = "后台博客分类管理接口文档")
public class TypeController {

    @Autowired
    private TypeService typeService;


//    新增或删除type
    @PostMapping("/types")
    @ApiOperation("新建分类接口文档")
    public Result post(@RequestBody Map<String, Type> para) {
        Type type = para.get("type");
        if (type.getId()==null){
            Type type1 = typeService.getTypeByName(para.get("type").getName());

            if (type1 != null) {
                return new Result(false, StatusCode.ERROR, "不能添加重复的分类", null);
            }
        } else {
            System.out.println("修改");
            List<Type> typeList = typeService.listByNameExceptSelf(type.getId(),type.getName());
            if (typeList.size() >0 ) {
                return new Result(false, StatusCode.ERROR, "分类名称已存在", null);
            }
        }
//        保存分类信息
        Type t = typeService.saveType(type);
//        修改分类信息,这里修改直接保存新的分类信息
//        Type t = typeService.updateType(type.getId(),type);
        if (t == null) {
            return new Result(false, StatusCode.ERROR, "修改失败", null);
        }
        return new Result(true, StatusCode.OK, "修改成功", null);
    }


    @GetMapping("/types/{id}/delete")
    @ApiOperation("删除分类接口文档")
    public Result delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

}
