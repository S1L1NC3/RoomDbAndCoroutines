package com.dmd.roomdbandcoroutines.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dmd.roomdbandcoroutines.databinding.ActivityMainBinding
import com.dmd.roomdbandcoroutines.enums.Enums
import com.dmd.roomdbandcoroutines.interfaces.UserDataAccessObject
import com.dmd.roomdbandcoroutines.models.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        //val mov = User("s","mail",20)

        DatabaseManager.getDatabase(applicationContext)


    }

    @Database(entities = arrayOf(User::class), version = 1)
    abstract class DatabaseManager : RoomDatabase() {
        companion object {
            private var instance: DatabaseManager? = null

            fun getDatabase(context: Context): DatabaseManager? {

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseManager::class.java,
                        Enums.DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }

}