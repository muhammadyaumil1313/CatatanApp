package org.d3if00001.catatanapp.notes.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int?=null,
    @ColumnInfo(name = "title")
    var title : String?=null,
    @ColumnInfo(name = "body")
    var body : String?=null,
    @ColumnInfo(name = "category")
    var category : String?=null,
    @ColumnInfo(name = "date")
    var date : String?=null,
) : Parcelable
