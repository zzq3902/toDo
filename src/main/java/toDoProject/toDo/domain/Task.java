package toDoProject.toDo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import toDoProject.toDo.dto.TaskDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "TASK_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    public Task() {
    }

    public Task(Member member, String title, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        this.member = member;
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public void update(TaskDTO taskDTO) {
        this.title = taskDTO.getTitle();
        this.startDate = taskDTO.getStartDate();
        this.startTime = taskDTO.getStartTime();
        this.endDate = taskDTO.getEndDate();
        this.endTime = taskDTO.getEndTime();
    }
}
