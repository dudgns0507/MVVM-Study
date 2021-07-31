package com.github.dudgns0507.mvvm_cropo.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface UserItemDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertUserItem(postItem: UserItem)

    @Delete
    suspend fun deleteUserItem(postItem: UserItem)

    @Query("DELETE FROM users")
    suspend fun deleteAllUserItem()

    @Query("SELECT * FROM users")
    fun observeAllUserItems(): LiveData<List<UserItem>>
}