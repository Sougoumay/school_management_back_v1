package univorleans.fr.projectcloudback.model;

import java.util.List;

public class Teacher extends Member {

    private List<String> modules;

    public Teacher(String lastName, String firstName, String email, int age, List<String> modules) {
        super(lastName, firstName, email, age);
        this.modules = modules;
    }

    public List<String> getModules() {
        return modules;
    }
}
