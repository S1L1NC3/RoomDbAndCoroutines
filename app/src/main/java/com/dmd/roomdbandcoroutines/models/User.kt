package com.dmd.roomdbandcoroutines.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dmd.roomdbandcoroutines.enums.Enums

@Entity(tableName = Enums.TABLE_NAME)
data class User(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = Enums.USER_ID) var userId: Int = 0,
                @ColumnInfo(name = Enums.NAME) var name: String?,
                @ColumnInfo(name = Enums.EMAIL) var email: String?,
                @ColumnInfo(name = Enums.AGE) var age: Int?)
