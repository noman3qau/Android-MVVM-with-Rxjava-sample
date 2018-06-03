package com.noman.mvvm.model.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
data class User(@PrimaryKey
                @ColumnInfo(name = "email")
                val email: String,
                @ColumnInfo(name = "firstName")
                val first: String,
                @ColumnInfo(name = "lastName")
                val last: String,
                @ColumnInfo(name = "address")
                val address: String,
                @ColumnInfo(name = "created")
                val created: String,
                @ColumnInfo(name = "balance")
                val balance: String
)
