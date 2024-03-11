package toDoProject.toDo.dto;

import lombok.Getter;
import lombok.Setter;
import toDoProject.toDo.domain.Member;
import toDoProject.toDo.domain.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberDTO {

    private Long id;
    private String name;
    private String username;
    private String password;
    private List<Task> tasks = new ArrayList<>();

    public MemberDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public MemberDTO() {
    }

    public Member getMember() {
        return new Member(name, username, password);
    }
}
