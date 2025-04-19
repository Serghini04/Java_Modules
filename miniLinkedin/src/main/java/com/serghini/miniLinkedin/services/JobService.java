package com.serghini.miniLinkedin.services;

import java.security.Provider;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serghini.miniLinkedin.models.JobPost;
import com.serghini.miniLinkedin.repository.JobRepo;


@Service
public class JobService {
    
    @Autowired
    private JobRepo repo;

    public void addJob(JobPost jobPost) {
        repo.addJob(jobPost);
    }

    public List<JobPost>   getAllJobs() {
        return repo.getAllJobs();
    }
}
