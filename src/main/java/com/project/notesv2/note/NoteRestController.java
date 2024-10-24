package com.project.notesv2.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
public class NoteRestController {
    @Autowired
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<?> getAllNote() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody NoteDTO noteDTO) {
        try {
            return ResponseEntity.ok(noteService.addNote(noteService.ConvertDTOtoNote(noteDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@RequestBody NoteDTO noteDTO, @PathVariable long id) {
        try {
            return ResponseEntity.ok(noteService.updateNote(noteService.ConvertDTOtoNote(noteDTO), id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable long id) {
        try {
            noteService.deleteNote(id);
            return ResponseEntity.ok("Записка была удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable long id) {
        try {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
        return ResponseEntity.ok().body(noteService.getNoteById(id));
        }
    }
}
