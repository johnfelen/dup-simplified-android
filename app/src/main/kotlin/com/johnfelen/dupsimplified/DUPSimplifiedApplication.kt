package com.johnfelen.dupsimplified

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.johnfelen.dupsimplified.model.repository.WorkoutRepository
import com.johnfelen.dupsimplified.model.service.WorkoutService
import com.johnfelen.dupsimplified.model.storage.database.WorkoutDatabase
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DUPSimplifiedApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(repositoryModule)
        import(storageModule)
        import(serviceModule)
    }

    private val repositoryModule = Kodein.Module("Repository") {
        bind() from provider { WorkoutRepository(instance(), instance()) }
    }

    private val storageModule = Kodein.Module("Storage") {
        bind() from singleton {
            Room.databaseBuilder(applicationContext, WorkoutDatabase::class.java, "WorkoutDatabase")
                .fallbackToDestructiveMigration()
                .build().workoutDao()
        }
    }

    private val serviceModule = Kodein.Module("Service") {
        bind() from provider { OkHttpClient.Builder().build() }

        bind() from provider {
            Retrofit.Builder()
                .client(instance())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
        }

        bind() from singleton {
            instance<Retrofit.Builder>()
                .baseUrl("http://daily-undulating-periodization.herokuapp.com/dupsimplified/workout/")
                .build()
                .create(WorkoutService::class.java)
        }
    }
}