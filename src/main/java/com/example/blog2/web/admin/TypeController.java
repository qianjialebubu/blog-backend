package com.example.blog2.web.admin;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.Type;
import com.example.blog2.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/4/4 11:21
 */

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class TypeController {

    @Autowired
    private TypeService typeService;

//    @GetMapping("/types/input")
//    public String input(Model model) {
//        model.addAttribute("type", new Type());
//        return "admin/types-input";
//    }

//    @GetMapping("types/{id}/input")
//    public String editInput(@PathVariable Long id, Model model) {
//        model.addAttribute("type", typeService.getType(id));
//        return "admin/types-input";
//    }

//    新增或删除type
    @PostMapping("/types")
    public Result post(@RequestBody Map<String, Type> para) {
        Type type = para.get("type");
        System.out.println("收到"+type);
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
        Type t = typeService.updateType(type.getId(),type);
        if (t == null) {
            return new Result(false, StatusCode.ERROR, "修改失败", null);
        }
        return new Result(true, StatusCode.OK, "修改成功", null);
    }


    @GetMapping("/types/{id}/delete")
    public Result delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

}
