package me.kqn.autolabel.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import me.kqn.autolabel.entity.AuthUser;
import me.kqn.autolabel.entity.ProjectsMember;
import me.kqn.autolabel.entity.ProjectsProject;
import me.kqn.autolabel.entity.ProjectsTag;
import me.kqn.autolabel.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kurt_kong
 * @since 2023-05-18
 */
@Controller
@RestController
public class ProjectsProjectController {
    @Autowired
    public ProjectsProjectMapper projectsProjectMapper;
    @Autowired
    public AuthUserMapper authUserMapper;
    /**创建新项目
     * */
    @PostMapping("/v1/projects")
    public JSONObject createProjects(@RequestBody JSONObject body){
        StpUtil.checkLogin();
        ProjectsProject project=new ProjectsProject();
        project.setCollaborativeAnnotation(body.getBoolean("collaborative_annotation"));
        project.setDescription(body.getString("description"));
        project.setGuideline(body.getString("guideline"));
        project.setName(body.getString("name"));
        project.setProjectType(body.getString("project_type"));
        project.setRandomOrder(body.getBoolean("random_order"));
        project.setSingleClassClassification(body.getBoolean("single_class_classification"));
        project.setCreatedById(Integer.parseInt((String) StpUtil.getLoginId()));
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        int project_id= projectsProjectMapper.maxID()+1;
        project.setId(project_id);
        JSONArray tags=body.getJSONArray("tags");
        for (int i = 0; i < tags.size(); i++) {
            ProjectsTag projectsTag=JSONObject.toJavaObject((JSONObject) tags.getJSONObject(i), ProjectsTag.class);
            projectsTag.setProjectId(project_id);
            projectsTagMapper.insert(projectsTag);
        }
        projectsProjectMapper.insert(project);

        ProjectsMember projectsMember=new ProjectsMember(0,LocalDateTime.now(),LocalDateTime.now(),project_id,1,Integer.parseInt((String) StpUtil.getLoginId()));
        projectsMemberMapper.insert(projectsMember);
        return toJSON(project);
    }
    @Autowired
    ProjectsMemberMapper projectsMemberMapper;
    @Autowired
    RolesRoleMapper rolesRoleMapper;
    @GetMapping("/v1/projects/{project_id}/my-role")
    public JSONObject getProjectMyRole(@PathVariable("project_id") Integer project_id){
        StpUtil.checkLogin();
        int id=project_id;
        Integer user= Integer.parseInt((String) StpUtil.getLoginId());
        Integer role_id=projectsMemberMapper.selectOne(new QueryWrapper<ProjectsMember>().eq("project_id",project_id).eq("user_id",user)).getRoleId();
        String username=authUserMapper.selectById(user).getUsername();
        String rolename=rolesRoleMapper.selectById(role_id).getName();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("user",user);
        jsonObject.put("role_id",role_id);
        jsonObject.put("username",username);
        jsonObject.put("rolename",rolename);
        return jsonObject;
    }
    @GetMapping("/v1/projects/{project_id}")
    public JSONObject getProject(@PathVariable("project_id") Integer project_id){
        return toJSON(projectsProjectMapper.selectById(project_id));
    }
    /**
     *
     * 列出所有项目
     * */
    @GetMapping("/v1/projects")
    public JSONObject Projects(@RequestParam("limit") int limit, @RequestParam("offset") int offset, @RequestParam("q") String q, @RequestParam("ordering") String ordering,  HttpServletRequest request){
       // StpUtil.checkLogin();
        if(ordering.equalsIgnoreCase("created_by")){
            ordering+="_id";
        }

        JSONObject res=new JSONObject();
        int count= Math.toIntExact(projectsProjectMapper.selectCount(new QueryWrapper<>()));
        res.put("count",count);

        String next=request.getRequestURL().toString()+"?limit=%s&offset=%s&q=&ordering=%s".formatted(limit,offset+limit,ordering);
        res.put("next",next);
        String previous=offset<=0?null:(request.getRequestURL().toString()+"?limit=%s&offset=%s&q=&ordering=%s".formatted(limit,Math.max(offset-limit,0),ordering));
        res.put("previous",previous);
        JSONArray results=new JSONArray();

        if(ordering.startsWith("-")){
            results=toJSONArray(projectsProjectMapper.getCreated_atDESC(limit,offset+1,ordering.substring(1)));
        }
        else {
            results=toJSONArray(projectsProjectMapper.getCreated_atASC(limit,offset+1,ordering));
        }

        res.put("results",results);

        return res;
    }
    public JSONArray toJSONArray(List<ProjectsProject> projects){
        JSONArray array=new JSONArray();
        for (ProjectsProject p:projects){
            array.add(toJSON(p));
        }
        return array;
    }
    @Autowired
    ProjectsTagMapper projectsTagMapper;
    public JSONObject toJSON(ProjectsProject project){
        JSONObject object=new JSONObject();
        object.put("id",project.getId());
        object.put("name",project.getName());
        object.put("description",project.getDescription());
        object.put("guideline",project.getGuideline());
        object.put("created_at",project.getCreatedAt());
        object.put("updated_at",project.getUpdatedAt());
        object.put("project_type",project.getProjectType());
        object.put("random_order",project.getRandomOrder());
        object.put("collaborative_annotation",project.getCollaborativeAnnotation());
        object.put("resourcetype",project.getProjectType()+"Project");
        boolean is_text_project=false;
        String[] text_project={"TextClassification","SequenceLabeling","Seq2seq","IntentDetectionAndSlotFilling"};
        for (int i=0;i<text_project.length;i++)if(project.getProjectType().equalsIgnoreCase(text_project[i])){
            is_text_project=true;
        }
        object.put("is_text_project",is_text_project);
        List<ProjectsTag> projectsTags=projectsTagMapper.selectList(new QueryWrapper<ProjectsTag>().eq("project_id",project.getId()));
        JSONArray tags= (JSONArray) JSONArray.toJSON(projectsTags);
        object.put("tags",tags);
        object.put("single_class_classification",project.getSingleClassClassification());
        AuthUser authUser= authUserMapper.selectOne(new QueryWrapper<AuthUser>().eq("id",project.getCreatedById()));
        object.put("author",authUser.getUsername());
        return object;
    }
}
