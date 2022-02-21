package com.wordie.alpha.repo;

import com.wordie.alpha.entity.WordsEntryEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface WordsRepo extends JpaRepository<WordsEntryEntity, Long> {

    @Nullable
    WordsEntryEntity findOneBySessionId(String sessionId);

}
