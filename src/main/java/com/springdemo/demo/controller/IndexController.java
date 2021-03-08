package com.springdemo.demo.controller;

import com.github.pagehelper.PageInfo;
import com.springdemo.demo.dto.QuestionDTO;
import com.springdemo.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page) {


        PageInfo<QuestionDTO> pagination = questionService.list(page);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
