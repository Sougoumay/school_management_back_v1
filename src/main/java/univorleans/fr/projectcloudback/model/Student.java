package univorleans.fr.projectcloudback.model;

public class Student extends Member {
    private String filiere;

    public Student(String lastName, String firstName, String email, int age, String filiere) {
        super(lastName, firstName, email, age);
        this.filiere = filiere;
    }

    public String getFiliere() {
        return filiere;
    }
}
