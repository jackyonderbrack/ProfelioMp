package com.miluconnect.profeliomp.data.repository.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun initDataStore(
    context: Context
) : DataStore<Preferences> = initDataStore(
    producePath = {
        context.filesDir.resolve(dataStoreFileName).absolutePath
    }
)