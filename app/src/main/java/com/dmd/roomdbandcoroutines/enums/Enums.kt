package com.dmd.roomdbandcoroutines.enums

class Enums {
    companion object Database{
        const val DATABASE_NAME = "sampleDatabase"
        const val TABLE_NAME = "sampleTableName"
        const val USER_ID = "userId"
        const val NAME = "name"
        const val EMAIL = "email"
        const val AGE = "age"
    }

    enum class SearchTypes{
        WITH_EMAIL,
        WITH_NAME,
        WITH_AGE,
    }
}