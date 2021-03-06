package com.farro.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.farro.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes", important = true))
                dao.insert(Task("Do the laundry"))
                dao.insert(Task("Do daily programming"))
                dao.insert(Task("Buy groceries", important = true))
                dao.insert(Task("Eat my favorite oatmeal", completed = true))
                dao.insert(Task("Call Roger Farro"))
                dao.insert(Task("Call Chin Gonzales"))
                dao.insert(Task("Do capstone/thesis", important = true))
                dao.insert(Task("Create questions for interview", important = true))
                dao.insert(Task("Play tong its go", completed = true))
            }
        }
    }
}