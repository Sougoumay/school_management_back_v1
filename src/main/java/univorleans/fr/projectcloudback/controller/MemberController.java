package univorleans.fr.projectcloudback.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import univorleans.fr.projectcloudback.model.Member;
import univorleans.fr.projectcloudback.model.Student;
import univorleans.fr.projectcloudback.model.Teacher;

import java.util.HashMap;
import java.util.List;

@RestController("/api")
public class MemberController {

    private HashMap<String, List<Student>> students;
    private HashMap<String, List<Teacher>> teachers;

    @GetMapping(name = "/members")
    public List<Member> getMembers() {
        return null;
    }


}
