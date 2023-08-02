package com.example.blog2.web.admin;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.Tag;
import com.example.blog2.po.Type;
import com.example.blog2.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@CrossOrigin
@Api(tags = "后台标签管理接口文档")
public class TagController {

    @Autowired
    private TagService tagService;

//    添加或修改标签
    @PostMapping("/tags")
    @ApiOperation("新建标签接口文档")
    public Result post(@RequestBody Map<String, Tag> para) {
        Tag tag = para.get("tag");
        if (tag.getId()!=null){
            Tag tag1 = tagService.getTagByName(tag.getName());

            if (tag1 != null) {
                return new Result(false, StatusCode.ERROR, "不能添加重复的标签",null);
            }
        } else {
            List<Tag> tagList = tagService.listByNameExceptSelf(tag.getId(),tag.getName());
            if (tagList.size() >0 ) {
                return new Result(false, StatusCode.ERROR, "分类名称已存在", null);
            }
        }

        Tag t = tagService.saveTag(tag);
        if (t == null) {
            return new Result(false, StatusCode.ERROR, "修改失败", null);
        }
        return new Result(true, StatusCode.OK, "修改成功", t);
    }


    @GetMapping("/tags/{id}/delete")
    @ApiOperation("根据id删除标签接口文档")
    public Result delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

}
