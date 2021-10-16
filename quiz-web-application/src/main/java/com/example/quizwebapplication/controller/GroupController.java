package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.AddGroupResponse;
import com.example.quizwebapplication.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping(value = "/api/group/score/file")
    public ResponseEntity<ByteArrayResource> downloadGroup() throws IOException {
        File file = groupService.downloadFile();
        if (file == null) {
            return ResponseEntity.status(500).body(null);
        }
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentLength(file.length())
                .contentType(new MediaType("text", "csv"))
                .body(resource);
    }
}
