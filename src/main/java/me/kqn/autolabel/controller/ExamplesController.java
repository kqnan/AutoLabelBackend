package me.kqn.autolabel.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/v1/projects/")
public class ExamplesController {
    @GetMapping("{project_id}/examples")
    public JSONObject ListExamples(@PathVariable("project_id") Integer project_id){
        return null;
    }
}
