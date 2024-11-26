package univorleans.fr.projectcloudback.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univorleans.fr.projectcloudback.model.Student;
import univorleans.fr.projectcloudback.model.Teacher;

import java.util.*;

@RestController("/api")
public class MemberController {

    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private Set<String> types = Set.of("student","teacher");

    @GetMapping(name = "/members")
    public ResponseEntity<List<?>> getMembers(@RequestParam String type,
                                     @RequestParam(required = false) String name) {

        students.clear();
        teachers.clear();
        students.addAll(generateStudents());
        teachers.addAll(generateTeachers());


        if (type == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!types.contains(type)) {
            return ResponseEntity.notFound().build();
        }

        if (type.equals("student")) {
            if (name == null) {
                return new ResponseEntity<>(students, HttpStatus.OK);
            }
            List<Student> studentsToReturn = new ArrayList<>();
            for (Student student : students) {
                if (student.getLastName().contains(name)) {
                    studentsToReturn.add(student);
                }
            }
            return new ResponseEntity<>(studentsToReturn, HttpStatus.OK);
        }

        if (name == null) {
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        }
        List<Teacher> teacherssToReturn = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getLastName().contains(name)) {
                teacherssToReturn.add(teacher);
            }
        }
        return new ResponseEntity<>(teacherssToReturn, HttpStatus.OK);
    }


    public static List<Student> generateStudents() {
        List<Student> students = new ArrayList<>();
        Random rand = new Random();
        String[] lastNames = {"Durand", "Dupont", "Martin", "Bernard", "Thomas", "Petit", "Lemoine", "Lemoine", "Richard", "Faure"};
        String[] firstNames = {"Alice", "Pierre", "Julien", "Marie", "Sophie", "Paul", "Luc", "Claire", "Antoine", "Caroline"};
        String[] filieres = {"Informatique", "Mathematiques", "Physique", "Chimie", "Biologie", "Mécanique", "Génie Civil", "Électronique", "Économie", "Droit"};

        for (int i = 0; i < 10; i++) {
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@univ.fr";
            int age = rand.nextInt(5) + 18; // Âge entre 18 et 22 ans
            String filiere = filieres[rand.nextInt(filieres.length)];

            students.add(new Student(lastName, firstName, email, age, filiere));
        }
        return students;
    }


    public static List<Teacher> generateTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        Random rand = new Random();
        String[] lastNames = {"Lemoine", "Martinez", "Dupont", "Leclerc", "Dufresne", "Roche", "Guillaume", "Robert", "Allard", "Boucher"};
        String[] firstNames = {"François", "Brigitte", "Luc", "Isabelle", "Michel", "Pierre", "Caroline", "Jean", "Thomas", "Julie"};
        List<String> allModules = Arrays.asList("Informatique", "Physique", "Mathématiques", "Chimie", "Biologie", "Génie Civil", "Mécanique", "Droit");

        for (int i = 0; i < 10; i++) {
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@univ.fr";
            int age = rand.nextInt(15) + 30; // Âge entre 30 et 45 ans
            List<String> modules = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                String module = allModules.get(rand.nextInt(allModules.size()));
                if (!modules.contains(module)) {
                    modules.add(module);
                }
            }

            teachers.add(new Teacher(lastName, firstName, email, age, modules));
        }
        return teachers;
    }


}
