package com.chersoft.simplenotes.domain.mappers;

import com.chersoft.simplenotes.data.models.NoteModel;
import com.chersoft.simplenotes.domain.models.Note;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NoteMapperTest {

    private static final String NOTE_TEXT = "my_note_text";
    private Note note;
    private NoteModel noteModel;

    @Before
    public void setUp() throws Exception{
        note = new Note(NOTE_TEXT);
        noteModel = new NoteModel();
        noteModel.setText(NOTE_TEXT);
    }

    @Test
    public void testMap(){
        assertEquals(note.getText(), NoteMapper.map(noteModel).getText());
    }

    @Test
    public void testReverse(){
        assertEquals(noteModel.getText(), NoteMapper.reverse(note).getText());
    }

}