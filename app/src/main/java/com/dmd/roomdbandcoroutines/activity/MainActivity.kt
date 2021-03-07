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
        val view = binding.root
        setContentView(view)

        val dbSample = DatabaseManager.getDatabase(applicationContext)
        binding.buttonInsert.setOnClickListener {
            val mov = User(12,"s","mail",20)
            dbSample?.userDao()?.insert(mov)
        }

        binding.buttonDelete.setOnClickListener {

        }

        binding.buttonSearch.setOnClickListener {

        }

        binding.buttonForDelay.setOnClickListener {

        }


    }

    @Database(entities = [User::class], version = 1)
    abstract class DatabaseManager : RoomDatabase() {
        abstract fun userDao(): UserDataAccessObject
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