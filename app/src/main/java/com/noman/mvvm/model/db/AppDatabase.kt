package com.noman.mvvm.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.noman.mvvm.model.data.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}