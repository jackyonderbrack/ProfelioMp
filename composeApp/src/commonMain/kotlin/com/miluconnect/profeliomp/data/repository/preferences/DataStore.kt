package com.miluconnect.profeliomp.data.repository.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

private lateinit var dataStore: DataStore<Preferences>
@OptIn(InternalCoroutinesApi::class)
private val lock = SynchronizedObject()

@OptIn(InternalCoroutinesApi::class)
fun initDataStore(producePath: () -> String): DataStore<Preferences> =
    synchronized(lock) {
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            androidx.datastore.preferences.core.PreferenceDataStoreFactory.createWithPath(
                produceFile = { producePath().toPath() }
            ).also { dataStore = it }
        }
    }

internal const val dataStoreFileName = "app.preferences_pb"