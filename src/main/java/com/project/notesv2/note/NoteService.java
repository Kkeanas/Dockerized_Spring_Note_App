package com.project.notesv2.note;

import com.project.notesv2.category.CategoryService;
import com.project.notesv2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(long id) {
        if (noteRepository.existsById(id)) {
            return noteRepository.getReferenceById(id);
        }
        throw new DataIntegrityViolationException("Записки с id " + id + " не существует");
    }

    public Note addNote(Note note) {
        try {
            return noteRepository.save(note);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Ошибка формы полученных данных");
        }
    }

    public Note updateNote(Note note, long id) {
        if (noteRepository.existsById(id)) {
            Note updatedNote = noteRepository.getReferenceById(id);
            updatedNote.setTitle(note.getTitle());
            updatedNote.setContent(note.getContent());
            updatedNote.setCategory(categoryService.getCategoryByName(note.getCategory()));
            noteRepository.save(updatedNote);
            return updatedNote;
        }
        throw new DataIntegrityViolationException("Записки с id" + id + " не сущестует");
    }

    public void deleteNote(long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        }
        throw new DataIntegrityViolationException("Записки с id " + id + " не существует");
    }

    public Note ConvertDTOtoNote(NoteDTO noteDTO) {
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setCategory(categoryService.getCategoryById(noteDTO.getCategory_id()));
        note.setUser(userService.findUserById(noteDTO.getUser_id()));
        return note;
    }
}
