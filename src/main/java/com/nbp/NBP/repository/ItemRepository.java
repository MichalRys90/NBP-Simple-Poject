package com.nbp.NBP.repository;

import com.nbp.NBP.domain.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query
    List<Item> findForAnyString(@Param("ANYSTRING") String anyString);

    @Query
    List<Item> sortByName();

    @Query
    List<Item> sortByPostingDate();

    List<Item> findAllByPostingDate(LocalDate date);
}
