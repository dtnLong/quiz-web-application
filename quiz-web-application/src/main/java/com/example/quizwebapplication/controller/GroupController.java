package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.AddGroupResponse;
import com.example.quizwebapplication.dto.SaveAnswerRequest;
import com.example.quizwebapplication.dto.SaveAnswerResponse;
import com.example.quizwebapplication.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping(value = "/api/group/add")
    public ResponseEntity<AddGroupResponse> saveAnswer(@RequestBody List<Map<String, String>> groups) {
        AddGroupResponse response = groupService.addGroup(groups);
        if (!response.isSuccess()) {
            return ResponseEntity.status(response.getStatus()).body(response);
        }

        return ResponseEntity.ok().body(response);
    }
}
