package com.github.dudgns0507.mvvm_cropo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserItem::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userItemDao(): UserItemDao
}