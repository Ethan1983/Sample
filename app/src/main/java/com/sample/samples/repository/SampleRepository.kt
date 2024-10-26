package com.sample.samples.repository

import android.util.Log
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

private const val TAG = "SampleRepository"

@Entity(tableName = "sample")
data class Sample(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val value: String,
    val additionalValue: String? = null
)

@Dao
interface SampleDao {
    @Query("SELECT * FROM sample")
    suspend fun getSamples(): List<Sample>

    @Insert
    suspend fun addSample(sample: Sample)
}

@ActivityRetainedScoped
class SampleRepository @Inject constructor(private val sampleDao: SampleDao) {

    init {
        Log.e(TAG, "Sample repository $this initialized")
    }

    fun getSampleValue(): String {
        return "Sample $this"
    }

    suspend fun addValue(value: String) {
        sampleDao.addSample(Sample(value = value))
    }

    suspend fun getSamples(): List<Sample> {
        return sampleDao.getSamples()
    }
}
