package univorleans.fr.projectcloudback.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import univorleans.fr.projectcloudback.model.Member;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemberController {

    private List<Member> students = new ArrayList<>();
    private List<Member> teachers = new ArrayList<>();
    private HashMap<String, List<Member>> membersMap = new HashMap<>();
    private boolean firstCall = true;

    @GetMapping("/members")
    public ResponseEntity<List<?>> getMembers(@RequestParam String type) {


        if (firstCall) {
            students.addAll(generateStudents());
            teachers.addAll(generateTeachers());
            membersMap.put("student", students);
            membersMap.put("teacher", teachers);
            firstCall = false;
        }

        if (type == null || type.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!membersMap.containsKey(type)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(membersMap.get(type), HttpStatus.OK);
    }


    public static List<Member> generateStudents() {
        List<Member> students = new ArrayList<>();
        Random rand = new Random();
        String[] lastNames = {"Durand", "Dupont", "Martin", "Bernard", "Thomas", "Petit", "Lemoine", "Lemoine", "Richard", "Faure"};
        String[] firstNames = {"Alice", "Pierre", "Julien", "Marie", "Sophie", "Paul", "Luc", "Claire", "Antoine", "Caroline"};

        for (int i = 0; i < 30; i++) {
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@etu.univ-orleans.fr";
            int age = rand.nextInt(5) + 18; // Âge entre 18 et 22 ans

            students.add(new Member(lastName, firstName, email, age));
        }
        return students;
    }


    public static List<Member> generateTeachers() {
        List<Member> teachers = new ArrayList<>();
        Random rand = new Random();
        String[] lastNames = {"Lemoine", "Martinez", "Dupont", "Leclerc", "Dufresne", "Roche", "Guillaume", "Robert", "Allard", "Boucher"};
        String[] firstNames = {"François", "Brigitte", "Luc", "Isabelle", "Michel", "Pierre", "Caroline", "Jean", "Thomas", "Julie"};

        for (int i = 0; i < 30; i++) {
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@univ.fr";
            int age = rand.nextInt(15) + 30; // Âge entre 30 et 45 ans

            teachers.add(new Member(lastName, firstName, email, age));
        }
        return teachers;
    }


}
