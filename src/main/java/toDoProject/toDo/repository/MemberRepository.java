package toDoProject.toDo.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import toDoProject.toDo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Override
    List<Member> findAll();

    @Override
    <S extends Member> S save(S entity);

    @Override
    Optional<Member> findById(Long id);

    @Override
    long count();

    @Override
    void delete(Member entity);

    Member findByUsername(String Username);
}
