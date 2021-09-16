package com.chersoft.simplenotes.domain.mappers;

import com.chersoft.simplenotes.data.models.NoteInfoModel;
import com.chersoft.simplenotes.domain.models.NoteInfo;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class NoteInfoMapperTest {

    private static final String NOTE_NAME = "noteName";
    private static final String NOTE_DATE = new Date().toString();
    private static final int BACKGROUND_INDEX = 2;
    private static final int TEXT_INDEX = 3;

    private NoteInfo noteInfo;
    private NoteInfoModel noteInfoModel;

    @Before
    public void setUp() throws Exception {
        noteInfo = new NoteInfo(NOTE_NAME, NOTE_DATE, BACKGROUND_INDEX, TEXT_INDEX);
        noteInfoModel = new NoteInfoModel(NOTE_NAME, NOTE_DATE, BACKGROUND_INDEX, TEXT_INDEX);
    }

    @Test
    public void map() {
        NoteInfo mapped = NoteInfoMapper.map(noteInfoModel);
        myAssertEquals(mapped, noteInfoModel);
    }

    @Test
    public void reverse() {
        NoteInfoModel mapped = NoteInfoMapper.reverse(noteInfo);
        myAssertEquals(noteInfo, mapped);
    }

    @Test
    public void mapList() {
        List<NoteInfoModel> infoModelList = new LinkedList<>();
        infoModelList.add(noteInfoModel);
        List<NoteInfo> mapped = NoteInfoMapper.mapList(infoModelList);
        assertEquals(mapped.size(), 1);
        myAssertEquals(mapped.get(0), infoModelList.get(0));
    }

    @Test
    public void reverseList() {
        List<NoteInfo> list = new LinkedList<>();
        list.add(noteInfo);
        List<NoteInfoModel> mapped = NoteInfoMapper.reverseList(list);
        assertEquals(mapped.size(), 1);
        myAssertEquals(list.get(0), mapped.get(0));
    }

    private void myAssertEquals(NoteInfo noteInfo, NoteInfoModel noteInfoModel){
        assertEquals(noteInfo.getName(), noteInfoModel.getName());
        assertEquals(noteInfo.getDate(), noteInfoModel.getDate());
        assertEquals(noteInfo.getBackgroundColorIndex(), noteInfoModel.getBackgroundColorIndex());
        assertEquals(noteInfo.getFontColorIndex(), noteInfoModel.getFontColorIndex());
    }
}