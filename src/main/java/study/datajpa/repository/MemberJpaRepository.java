package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import study.datajpa.entity.Member;

@Repository
@Transactional(readOnly = true)
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Transactional
    public void delete(Member member) {
        entityManager.remove(member);
    }

    public List<Member> findAll() {
        return entityManager.createQuery(
                "select m from Member m", Member.class
        ).getResultList();
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    public long count() {
        return entityManager.createQuery(
                "select count(m) from Member m", Long.class
        ).getSingleResult();
    }

    public Member find(Long id) {
        return entityManager.find(Member.class, id);
    }

    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return entityManager.createQuery(
                        "select m from Member m where m.username = :username and m.age > :age", Member.class
                ).setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }
}
