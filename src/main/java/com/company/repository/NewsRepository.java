package com.company.repository;

import com.company.domain.basic.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("select n from News n where n.deleted = false")
    List<News> findFirstByDeletedFalse();

}
