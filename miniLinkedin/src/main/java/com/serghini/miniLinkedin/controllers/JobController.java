package com.serghini.miniLinkedin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

import com.serghini.miniLinkedin.models.JobPost;
import com.serghini.miniLinkedin.services.JobService;


@Controller
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping({"/", "home"})
    public String index() {
        return "home";
    }

    @GetMapping("addJob")
    public String addJob() {
        return "addJob";
    }

    @PostMapping("handleForm")
    public String handleForm(JobPost jobPost) {
        service.addJob(jobPost);
        return "success";
    }

    @GetMapping("viewAllJobs")
    public  String viewAllJobs(Model model) {
        model.addAttribute("jobPosts", service.getAllJobs());
        return "viewAllJobs";
    }
}
