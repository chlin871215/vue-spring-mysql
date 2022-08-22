package com.example.springwebservice.model;

import com.example.springwebservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);

    Long deleteById(int id);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByOrderByAgeDesc();

    @Query(value = "SELECT * FROM user WHERE name=:name AND age=:age", nativeQuery = true)
    List<User> findByNameAndAge(@Param("name") String name, @Param("age") int age);

    @Modifying
    @Query(value = "INSERT INTO user VALUES(?1,?2,?3)", nativeQuery = true)
    @Transactional
    void createUser(int id, String name, int age);

    @Modifying
    @Query(value = "UPDATE user SET name=?2 , age=?3 WHERE id=?1", nativeQuery = true)
    @Transactional
    void updateUser(int id, String name, int age);

    @Modifying
    @Query(value = "DELETE FROM user WHERE name=?1 AND age=?2", nativeQuery = true)
    @Transactional
    void deleteUser(String name,int age);

}
