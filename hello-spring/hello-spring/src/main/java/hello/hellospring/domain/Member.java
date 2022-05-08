package hello.hellospring.domain;
import javax.persistence.*;

//Entity 어노테이션으로 jpa가 관리하는 Entity임을 알린다
@Entity
public class Member {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  //id가 pk(primary key)이고 db에서 생성되는 키임을 알려 매핑한다.
    private Long id;
   @Column(name="username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
