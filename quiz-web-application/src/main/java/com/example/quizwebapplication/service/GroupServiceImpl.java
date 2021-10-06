package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.AddGroupResponse;
import com.example.quizwebapplication.entity.Group;
import com.example.quizwebapplication.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public AddGroupResponse addGroup(List<Map<String, String>> groups) {
        AddGroupResponse response = new AddGroupResponse();
        for (Map<String, String> group: groups) {
            Group newGroup = new Group();
            newGroup.setAttempt((short) 0);
            newGroup.setName(group.get("group"));
            newGroup.setLeaderEmail(group.get("email"));
            newGroup.setScore((long) 0);
            groupRepository.save(newGroup);
        }
        response.setStatus(200);
        response.setSuccess(true);
        return response;
    }
}
