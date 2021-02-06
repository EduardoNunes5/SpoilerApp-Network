package com.eduardo.spoilerappnetwork.spoiler.repository;

import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.eduardo.spoilerappnetwork.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpoilerRepository extends JpaRepository<Spoiler, Long> {

    Optional<Spoiler> findByIdAndAuthor(Long id, User author);
    void deleteByIdAndAuthor(Long id, User author);
    List<Spoiler> findAllByNameContaining(String name);
}
