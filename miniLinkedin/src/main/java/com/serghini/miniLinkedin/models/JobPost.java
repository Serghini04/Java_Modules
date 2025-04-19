package com.serghini.miniLinkedin.models;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @Data (from Lombok)
//     This is a Lombok annotation that generates all boilerplate code for you:
//         getters for all fields
//         setters for all fields
//         toString() method
//         equals() and hashCode()
@NoArgsConstructor
@AllArgsConstructor
@Component
// @Component (from Spring Framework)
//     Marks this class as a Spring Bean (i.e., a managed object by the Spring container).
public class JobPost {
    private int postId;
    private String postProfile;
    private String postDesc;
    private int reqExperience;
    private List<String> postTechStack;
}