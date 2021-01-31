package com.eduardo.spoilerappnetwork.spoiler.repository;

import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpoilerRepository extends JpaRepository<Spoiler, Long> {
}
