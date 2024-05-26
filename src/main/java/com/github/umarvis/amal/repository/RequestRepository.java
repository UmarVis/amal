package com.github.umarvis.amal.repository;

import com.github.umarvis.amal.entities.Request;
import com.github.umarvis.amal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findAllByRequesterOrderByCreated(User user);
}
