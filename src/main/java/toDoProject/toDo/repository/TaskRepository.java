package toDoProject.toDo.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toDoProject.toDo.domain.Task;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {
    private final EntityManager em;

    public Task findById(long id) {
        return em.find(Task.class, id);
    }

    public List<Task> findAll() {
        return em.createQuery("select s from schedule s")
                .getResultList();
    }

    public void save(Task task) {
        em.persist(task);
    }

    public void remove(Task task) {
        em.remove(task);
    }
}
