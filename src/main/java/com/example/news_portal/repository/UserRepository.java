package com.example.news_portal.repository;

import com.example.news_portal.dto.request.UpdateProfileRequest;
import com.example.news_portal.dto.response.UpdateProfileResponse;
import com.example.news_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.nickname = :name")
    Optional<User> findByName(String name);

    @Modifying
    @Transactional
    @Query("update User set " +
            "name = :name," +
            "surname = :surname," +
            "nickname = :nick," +
            "photo = :photo where id = :id")
    void updateProfile(@Param("id") Long id,
                                        @Param("name") String name,
                                        @Param("surname") String surname,
                                        @Param("nick") String nickname,
                                        @Param("photo") String photo);
}