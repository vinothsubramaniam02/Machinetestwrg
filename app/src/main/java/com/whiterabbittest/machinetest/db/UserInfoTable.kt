package com.whiterabbittest.machinetest.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserInfoTable(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_id") val user_id: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "profile_image") val profile_image: String?,
    @ColumnInfo(name = "street") val street: String?,
    @ColumnInfo(name = "suite") val suite: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "zipcode") val zipcode: String?,
    @ColumnInfo(name = "lat") val lat: String?,
    @ColumnInfo(name = "lng") val lng: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "website") val website: String?,
    @ColumnInfo(name = "company_name") val company_name: String?,
    @ColumnInfo(name = "company_catchPhrase") val company_catchPhrase: String?,
    @ColumnInfo(name = "bs") val bs: String?
)
