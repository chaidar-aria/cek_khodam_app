package com.chaidar.cekkodam

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES = "CREATE TABLE ${MyContract.MyEntry.TABLE_NAME} (" +
                "${MyContract.MyEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                "${MyContract.MyEntry.COLUMN_NAME_KHODAM} TEXT)"

        db.execSQL(SQL_CREATE_ENTRIES)

        // Insert data into the table
        val khodams = arrayOf(
            "raksasa hijau", "ketupat", "singa introvert", "ketupat sayur", "naga putih sangar",
            "kak gem", "kepala charger", "google maps", "sosok hitam", "kayu jati",
            "malika", "sapi", "rawa rontek", "kosong", "sumber bolong", "kadal chuby",
            "penyu", "pohon palem", "naga bearbrend", "tahu goreng", "pesulap merah",
            "toji kerupuk", "abang roy", "pohon bijak", "landak trenggalek", "dudung",
            "nyi blorong","kucing garong","robot kentang","ular pelangi","bajaj lepas",
            "ayam geprek","ninja tua","kecoa galak","spongebob galau","unicorn berduri",
            "kura-kura ninja","tikus berjanggut","gajah terbang","bola salju jatuh","robot canggih",
            "nasi goreng gila"
        )

        for ((index, khodam) in khodams.withIndex()) {
            val values = ContentValues().apply {
                put(MyContract.MyEntry.COLUMN_NAME_ID, index + 1)
                put(MyContract.MyEntry.COLUMN_NAME_KHODAM, khodam)
            }
            db.insert(MyContract.MyEntry.TABLE_NAME, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply discard the data and start over
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MyContract.MyEntry.TABLE_NAME}"
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "KhodamData.db"
    }
}
