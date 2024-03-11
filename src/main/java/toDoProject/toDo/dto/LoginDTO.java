package toDoProject.toDo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO() {

    }public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
