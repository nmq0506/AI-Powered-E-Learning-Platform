package com.ou.ailearning.repository;

import com.ou.ailearning.entity.User;
import com.ou.ailearning.entity.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:search IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:role IS NULL OR u.role = :role)")
    Page<User> findAllBySearchAndRole(@Param("search") String search,
                                      @Param("role") Role role,
                                      Pageable pageable);
}