package com.pimmpo.passwordmanager.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pimmpo.passwordmanager.entity.Note;
import com.pimmpo.passwordmanager.entity.User;
import com.pimmpo.passwordmanager.repository.NoteRepository;
import com.pimmpo.passwordmanager.request.CreateNoteRequest;
import com.pimmpo.passwordmanager.request.GetNoteRequest;
import com.pimmpo.passwordmanager.request.UpdateNoteRequest;

import lombok.RequiredArgsConstructor;

/**
 * @author mavrin
 * @created 25.05.2022
 */
@Service
@RequiredArgsConstructor
public class NoteService {
    
    private final UserService userService;
    
    private final NoteRepository noteRepository;
    
    public List<Note> getNotesByUsername(String username) {

        User user = userService.getUserByUsername(username);
        return noteRepository.findByUser(user);
    }
    
    public Note createNoteForUser(String username, CreateNoteRequest request) {
        User user = userService.getUserByUsername(username);
        
        Note note = new Note();
        note.setLogin(request.getLogin());
        note.setPassword(request.getPassword());
        note.setUser(user);
        note.setUrl(request.getUrl());
        note.setMark(request.getMark());
        
        return noteRepository.save(note);
    }
    
    public void deleteNoteById(GetNoteRequest request) {
        noteRepository.deleteById(request.getNoteId());
    }
    
    public Note updateNote(UpdateNoteRequest request) {

        Note note = noteRepository.findById(request.getNoteId())
                .orElseThrow(() -> new IllegalArgumentException("Note didn't find"));

        if(Objects.nonNull(request.getLogin())) {
            note.setLogin(request.getLogin());
        }
        if(Objects.nonNull(request.getPassword())) {
            note.setPassword(request.getPassword());
        }
        if(Objects.nonNull(request.getUrl())) {
            note.setUrl(request.getUrl());
        }
        if(Objects.nonNull(request.getMark())) {
            note.setMark(request.getMark());
        }
        
        return noteRepository.save(note);
    }
    
    public Note getNoteById(GetNoteRequest request) {
        return noteRepository.findById(request.getNoteId())
                .orElseThrow(() -> new IllegalArgumentException("Note didn't found"));
    }
}
