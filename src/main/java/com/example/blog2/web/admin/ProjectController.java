package com.example.blog2.web.admin;

import com.example.blog2.dao.ProjectRepository;
import com.example.blog2.po.Essay;
import com.example.blog2.po.Project;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
@Api(tags = "后台项目管理接口文档")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @GetMapping("/project/{id}/delete")
    public Result delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new Result(true, StatusCode.OK, "删除项目成功",null );
    }

    @GetMapping("/projects")
    @ApiOperation("获取项目列表接口文档")
    public Result projects() {
        return new Result(true, StatusCode.OK, "获取项目列表成功", projectService.listProject());
    }

    @PostMapping("/project")
    @ApiOperation("新增项目接口文档")
    public Result post(@RequestBody Map<String, Project> para){
        System.out.println(para);
        Project project = para.get("project");
        Project p;
        if (project.getId() == null){
            p = projectService.saveProject(project);
        } else {
            p = projectService.updateProject(project.getId(),project);
        }
        if (p == null) {
            return new Result(false,StatusCode.ERROR,"操作失败");
        }
        return new Result(true,StatusCode.OK,"操作成功");
    }
}
