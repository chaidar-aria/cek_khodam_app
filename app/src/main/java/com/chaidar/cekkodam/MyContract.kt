package com.chaidar.cekkodam

import android.provider.BaseColumns

object MyContract {
    // Table contents are grouped together in an anonymous object.
    object MyEntry : BaseColumns {
        const val TABLE_NAME = "khodams"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_NAME_KHODAM = "nama_khodam"
    }
}
