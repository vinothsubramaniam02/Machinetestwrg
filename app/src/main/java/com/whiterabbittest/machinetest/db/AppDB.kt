package com.whiterabbittest.machinetest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.whiterabbittest.machinetest.localdb.UserInfoTable
import com.whiterabbittest.machinetest.localdb.UserInfoTableDao


@Database(entities = arrayOf(UserInfoTable::class),version = 3, exportSchema = false)
abstract  class AppDB: RoomDatabase() {

    abstract fun userInfoTable() : UserInfoTableDao

    companion object{
        @Volatile
        private var INSTANCE : AppDB? = null
        fun getDatabaseClient(context: Context): AppDB
        {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this)
            {
                INSTANCE = Room.databaseBuilder(context,AppDB::class.java,"UserInfo").fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                return INSTANCE!!
            }
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}