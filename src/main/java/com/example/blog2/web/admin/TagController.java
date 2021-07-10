package com.example.blog2.web.admin;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.Tag;
import com.example.blog2.po.Type;
import com.example.blog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/4/1 11:41
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class TagController {

    @Autowired
    private TagService tagService;

//    添加或修改标签
    @PostMapping("/tags")
    public Result post(@RequestBody Map<String, Tag> para) {
        Tag tag = para.get("tag");
        if (tag.getId()!=null){
            Tag tag1 = tagService.getTagByName(tag.getName());
            if (tag1 != null) {
                return new Result(false, StatusCode.ERROR, "不能添加重复的标签", null);
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
        return new Result(true, StatusCode.OK, "修改成功", null);
    }

    @PostMapping("/tags/{id}")
    public Result editPost(@RequestBody Map<String, Object> para) {
        Tag tag1 = tagService.getTagByName((String) para.get("name"));
        if (tag1 != null) {
            return new Result(false, StatusCode.ERROR, "此标签已存在", null);
        }
        Tag tag = new Tag();
        tag.setName((String) para.get("name"));
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            return new Result(false, StatusCode.ERROR, "修改失败", null);
        }
        return new Result(true, StatusCode.OK, "修改成功", null);
    }


    @GetMapping("/tags/{id}/delete")
    public Result delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

}
