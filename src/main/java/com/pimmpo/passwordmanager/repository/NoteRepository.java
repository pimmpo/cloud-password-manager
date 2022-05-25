package com.pimmpo.passwordmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pimmpo.passwordmanager.entity.Note;
import com.pimmpo.passwordmanager.entity.User;

/**
 * @author mavrin
 * @created 25.05.2022
 */
@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByUser(User user);
}
