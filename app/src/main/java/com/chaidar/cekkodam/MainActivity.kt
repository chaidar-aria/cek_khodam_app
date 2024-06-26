package com.chaidar.cekkodam

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: MyDBHelper
    private lateinit var inputNama: EditText
    private lateinit var displayText: TextView

    private var lastGeneratedName: String? = null
    private var lastGeneratedKhodam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize DBHelper
        dbHelper = MyDBHelper(this)

        // Initialize UI elements
        inputNama = findViewById(R.id.input_nama)
        displayText = findViewById(R.id.display_text)
    }

    fun fetchDataFromSQLite(view: View) {
        val nama = inputNama.text.toString().trim()

        if (nama.isEmpty()) {
            Toast.makeText(this, "Silakan masukkan nama terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }

        if (nama == lastGeneratedName) {
            Toast.makeText(this, "Nama sudah digenerate sebelumnya", Toast.LENGTH_SHORT).show()
            return
        }

        val db: SQLiteDatabase = dbHelper.readableDatabase

        val projection = arrayOf(MyContract.MyEntry.COLUMN_NAME_KHODAM)
        val sortOrder = "${MyContract.MyEntry.COLUMN_NAME_ID} ASC"

        val cursor: Cursor = db.query(
            MyContract.MyEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val khodams = mutableListOf<String>()
        while (cursor.moveToNext()) {
            val khodam = cursor.getString(cursor.getColumnIndexOrThrow(MyContract.MyEntry.COLUMN_NAME_KHODAM))
            khodams.add(khodam)
        }

        cursor.close()

        // Generate random index
        val randomIndex = Random.nextInt(khodams.size)
        val randomKhodam = khodams[randomIndex]

        // Update last generated data
        lastGeneratedName = nama
        lastGeneratedKhodam = randomKhodam

        // Display result
        displayText.text = "Nama: $nama\nKhodam: $randomKhodam"
    }
}
