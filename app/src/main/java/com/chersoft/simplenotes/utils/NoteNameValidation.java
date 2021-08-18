package com.chersoft.simplenotes.utils;

public class NoteNameValidation {

    public static boolean noteNameIsValid(String name){
        return !(name.contains("\\") || name.contains("/"));
    }

}
