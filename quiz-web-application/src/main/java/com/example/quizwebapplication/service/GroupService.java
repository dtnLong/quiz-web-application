package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.AddGroupResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GroupService {
    AddGroupResponse addGroup(List<Map<String, String>> groups);
    File downloadFile();
}
