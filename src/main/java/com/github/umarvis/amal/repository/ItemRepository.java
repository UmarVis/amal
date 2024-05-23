package com.github.umarvis.amal.repository;

import com.github.umarvis.amal.entities.Item;
import com.github.umarvis.amal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAllByOwnerOrderById(User user);

    @Query("select i from Item i where lower (i.name) like lower (concat('%', :text, '%')) " +
            "or lower (i.description) like lower (concat('%', :text, '%'))")
    List<Item> findAllByText(@Param("text") String text);
}
