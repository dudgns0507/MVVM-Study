package com.github.dudgns0507.mvvm_cropo.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PostItemDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertPostItem(postItem: PostItem)

    @Delete
    suspend fun deletePostItem(postItem: PostItem)

    @Query("DELETE FROM post_items")
    suspend fun deleteAllPostItem()

    @Query("SELECT * FROM post_items")
    fun observeAllPostItems(): LiveData<List<PostItem>>
}