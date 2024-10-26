package com.sample.samples.di

import android.content.Context
import androidx.room.Room
import com.sample.samples.repository.AppDatabase
import com.sample.samples.repository.SampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase").build()
    }

    @Provides
    fun providesSampleDao(database: AppDatabase): SampleDao {
        return database.sampleDao()
    }
}
