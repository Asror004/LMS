package com.company.repository;

import com.company.domain.basic.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("select n from News n where n.deleted = false order by n.createdAt desc")
    List<News> findAllByDeletedFalseInDescendingOrder();

    @Query("select n from News n where n.deleted = false order by n.createdAt desc")
    Page<News> findAllByDeletedFalseInDescendingOrder2(Pageable pageable);


    @Query("select n from News n where n.deleted = false")
    List<News> findAllByDeletedFalse();

    @Query("select n from News n where n.deleted = false and n.id = ?1")
    News findFirstByDeletedFalseAndId(Integer id);



}
