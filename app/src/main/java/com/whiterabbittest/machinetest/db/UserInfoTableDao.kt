package com.whiterabbittest.machinetest.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserInfoTableDao {



    @Query("SELECT * FROM UserInfoTable ")
     fun getAll(): List<UserInfoTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(vararg userInfoTable: UserInfoTable)

    @Query("SELECT * FROM UserInfoTable WHERE name LIKE '%' || :s || '%'" +
            "OR email LIKE  '%' || :s || '%'"
    )
    fun getItemName(s : String?): List<UserInfoTable>

    @Query("SELECT * FROM UserInfoTable WHERE user_id = :u_id" )
    fun getUserDetails(u_id: String): List<UserInfoTable>
}