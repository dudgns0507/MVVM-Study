package com.github.dudgns0507.mvvm_cropo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PostItem::class],
    version = 1
)
abstract class PostItemDatabase : RoomDatabase() {
    abstract fun postItemDao(): PostItemDao
}