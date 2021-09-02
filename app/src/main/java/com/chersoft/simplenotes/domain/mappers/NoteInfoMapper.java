package com.chersoft.simplenotes.domain.mappers;

import com.chersoft.simplenotes.data.models.NoteInfoModel;
import com.chersoft.simplenotes.domain.models.NoteInfo;

import java.util.ArrayList;
import java.util.List;

public class NoteInfoMapper {

    public static NoteInfo map(NoteInfoModel noteInfoModel){
        return new NoteInfo(
                noteInfoModel.getName(),
                noteInfoModel.getDate(),
                noteInfoModel.getColorIndex()
        );
    }

    public static NoteInfoModel reverse(NoteInfo noteInfo){
        return new NoteInfoModel(
                noteInfo.getName(),
                noteInfo.getDate(),
                noteInfo.getColorIndex()
        );
    }

    public static ArrayList<NoteInfo> mapList(List<NoteInfoModel> noteInfoModels){
        ArrayList<NoteInfo> result = new ArrayList<>(noteInfoModels.size());
        for (NoteInfoModel noteInfoModel : noteInfoModels){
            result.add(map(noteInfoModel));
        }
        return result;
    }

    public static ArrayList<NoteInfoModel> reverseList(List<NoteInfo> noteInfos){
        ArrayList<NoteInfoModel> result = new ArrayList<>(noteInfos.size());
        for (NoteInfo noteInfo : noteInfos){
            result.add(reverse(noteInfo));
        }
        return result;
    }

}
