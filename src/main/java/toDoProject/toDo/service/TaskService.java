package toDoProject.toDo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toDoProject.toDo.domain.Task;
import toDoProject.toDo.dto.TaskDTO;
import toDoProject.toDo.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    public Task findOne(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public Long join(TaskDTO taskDTO){
        Task task = taskDTO.getTask();
        taskRepository.save(task);
        return task.getId();
    }

    @Transactional
    public void delete(Long id) {
        Task task = taskRepository.findById(id);
        taskRepository.remove(task);
    }

    @Transactional
    public void update(Long id, TaskDTO taskDTO) {
        Task task = findOne(id);
        task.update(taskDTO);
    }
}
