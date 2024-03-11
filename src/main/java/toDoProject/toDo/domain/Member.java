package toDoProject.toDo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import toDoProject.toDo.dto.MemberDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String username;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Task> tasks = new ArrayList<>();

    public Member() {
    }

    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void update(MemberDTO memberDTO) {
        this.name = memberDTO.getName();
        this.username = memberDTO.getUsername();
        this.password = memberDTO.getPassword();
    }
}

