package com.dmd.roomdbandcoroutines.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dmd.roomdbandcoroutines.R
import com.dmd.roomdbandcoroutines.databinding.ActivityMainBinding
import com.dmd.roomdbandcoroutines.enums.Enums
import com.dmd.roomdbandcoroutines.interfaces.UserDataAccessObject
import com.dmd.roomdbandcoroutines.models.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbSample: DatabaseManager
    private lateinit var users: List<User>
    private var delayForCoroutines: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dbSample = DatabaseManager.getDatabase(applicationContext)
        binding.buttonInsert.setOnClickListener {
            val mov = User(12,"s","mail",20)
            dbSample.userDao().insert(mov)
        }

        binding.buttonDelete.setOnClickListener {

        }

    }

    @Database(entities = [User::class], version = 1)
    abstract class DatabaseManager : RoomDatabase() {
        abstract fun userDao(): UserDataAccessObject
        companion object {
            private var instance: DatabaseManager? = null

            fun getDatabase(context: Context): DatabaseManager {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseManager::class.java,
                        Enums.DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
                return instance as DatabaseManager
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonSearchWithEmail -> searchDb(Enums.SearchTypes.WITH_EMAIL)
            R.id.buttonSearchWithName -> {
                GlobalScope.launch {
                    delay(delayForCoroutines.toLong())
                    searchDb(Enums.SearchTypes.WITH_NAME)
                }
            }
            R.id.buttonSearchWithAge -> searchDb(Enums.SearchTypes.WITH_AGE)
            R.id.buttonForDelay -> setDelay()
        }
    }

    private fun setDelay(){
        try {
            delayForCoroutines = binding.editTextDelay.text.toString().toInt()
            if (delayForCoroutines < 1000){ //That means input was NOT in milliseconds
                delayForCoroutines *= 1000
            } //Otherwise it means it's in milliseconds no need to make assignment
        } catch (exception: NumberFormatException){
            Toast.makeText(applicationContext, "${binding.editTextDelay.text} ${applicationContext.resources.getString(R.string.is_not_a_number)}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchDb(searchType: Enums.SearchTypes){
        Log.d("SearchTypesEnumTrackLog", "searchDb: $searchType")
        users = when(searchType){
            Enums.SearchTypes.WITH_EMAIL -> dbSample.userDao().findByEMail("lol")
            Enums.SearchTypes.WITH_NAME -> dbSample.userDao().findByName("lol")
            Enums.SearchTypes.WITH_AGE -> dbSample.userDao().findByAge(10)
        }

    }

}