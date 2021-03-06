package com.dmd.roomdbandcoroutines.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dmd.roomdbandcoroutines.enums.Enums
import com.dmd.roomdbandcoroutines.models.User

@Dao
public interface UserDataAccessObject {
    @Query("SELECT * FROM ${Enums.TABLE_NAME}")
    fun getAll(): List<User>

    @Query("SELECT * FROM ${Enums.TABLE_NAME} WHERE ${Enums.NAME} LIKE :name")
    fun findByName(name: String): List<User>

    @Query("SELECT * FROM ${Enums.TABLE_NAME} WHERE ${Enums.NAME} LIKE :email")
    fun findByEMail(email: String) : List<User>

    @Query("SELECT * FROM ${Enums.TABLE_NAME} WHERE ${Enums.AGE} LIKE :age")
    fun findByAge(age: Int): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Insert
    fun insert(vararg user: User)

    @Delete
    fun delete(user: User)
}