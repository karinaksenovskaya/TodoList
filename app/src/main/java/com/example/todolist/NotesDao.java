package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NotesDao{
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getNotes();

    @Insert
    Completable add(Note note);

    @Query("DELETE FROM note WHERE id = :id")
    Completable remove(int id);

}
