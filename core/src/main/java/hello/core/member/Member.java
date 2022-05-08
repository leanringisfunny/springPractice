package hello.core.member;

public class Member {
    private Long Id;
    private Grade grade;
    private String Name;

    public Member(Long id, Grade grade, String name) {
        Id = id;
        this.grade = grade;
        Name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}






