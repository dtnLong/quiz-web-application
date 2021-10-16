package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.AddGroupResponse;
import com.example.quizwebapplication.entity.Group;
import com.example.quizwebapplication.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    @Override
    public File downloadFile() {
        File file = new File("score.csv");
        try {
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();
            sb.append("Group name");
            sb.append(',');
            sb.append("Score");
            sb.append('\n');

            List<Group> groupList = groupRepository.findAll();
            for (Group group : groupList) {
                sb.append(group.getName());
                sb.append(',');
                sb.append(group.getScore().toString());
                sb.append('\n');
            }
            writer.write(sb.toString());
            writer.close();
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
