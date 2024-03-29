package com.company.repository;

import com.company.domain.basicsOfBasics.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language findByCodeIgnoreCase(String code);
}
