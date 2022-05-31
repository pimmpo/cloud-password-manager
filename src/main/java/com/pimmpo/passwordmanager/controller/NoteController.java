package com.pimmpo.passwordmanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pimmpo.passwordmanager.entity.Note;
import com.pimmpo.passwordmanager.request.CreateNoteRequest;
import com.pimmpo.passwordmanager.request.GetNoteRequest;
import com.pimmpo.passwordmanager.request.UpdateNoteRequest;
import com.pimmpo.passwordmanager.service.NoteService;
import com.pimmpo.passwordmanager.util.JwtUtils;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author mavrin
 * @created 25.05.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
@Tag(name = "Создание записи")
public class NoteController {

    private final NoteService noteService;
    private final JwtUtils jwtUtils;
    
    @PostMapping()
    public Note createNote(@RequestBody CreateNoteRequest createNoteRequest, HttpServletRequest request) {

        String username = jwtUtils.getUserNameFromHeader(request.getHeader("Authorization"));
        return noteService.createNoteForUser(username, createNoteRequest);
    }
    
    @GetMapping("/all")
    public List<Note> getAllNotesByUser(HttpServletRequest request) {
        String username = jwtUtils.getUserNameFromHeader(request.getHeader("Authorization"));
        return noteService.getNotesByUsername(username);
    }
    
    @DeleteMapping()
    public void deleteById(@RequestBody GetNoteRequest deleteRequest) {
        noteService.deleteNoteById(deleteRequest);
    }
    
    @PatchMapping()
    public Note updateNote(@RequestBody UpdateNoteRequest updateNoteRequest) {
        return noteService.updateNote(updateNoteRequest);
    }
    
    @GetMapping("/{noteId}")
    public Note getById(@PathVariable long noteId) {
        return noteService.getNoteById(new GetNoteRequest(noteId));
    }
}
