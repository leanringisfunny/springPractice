package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//lombok은 getter와 setter를 생서하지 않고도 제공해줌
@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdasdsaf");
        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);
    }
}
