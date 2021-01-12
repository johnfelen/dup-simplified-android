package com.johnfelen.dupsimplified

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.johnfelen.dupsimplified.model.repository.WorkoutRepository
import com.johnfelen.dupsimplified.model.service.WorkoutService
import com.johnfelen.dupsimplified.model.storage.database.WorkoutDatabase
import com.johnfelen.dupsimplified.view.adapter.LiftListAdapter
import com.johnfelen.dupsimplified.view.adapter.MovementPatternListAdapter
import com.johnfelen.dupsimplified.view.adapter.PlateListAdapter
import com.johnfelen.dupsimplified.viewmodel.WorkoutViewModel
import com.johnfelen.dupsimplified.viewmodel.ViewModelFactory
import com.johnfelen.dupsimplified.viewmodel.bindViewModel
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
        import(adapterModule)
        import(viewModelModule)
        import(repositoryModule)
        import(storageModule)
        import(serviceModule)
    }

    private val adapterModule = Kodein.Module("Adapter") {
        bind() from provider { MovementPatternListAdapter() }
        bind() from provider { LiftListAdapter() }
        bind() from provider { PlateListAdapter() }
    }

    private val viewModelModule = Kodein.Module("ViewModel") {
        bind() from singleton { ViewModelFactory(kodein) }
        bindViewModel<WorkoutViewModel>() with provider { WorkoutViewModel(instance()) }
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
                .baseUrl("https://daily-undulating-periodization.herokuapp.com/")
                .build()
                .create(WorkoutService::class.java)
        }
    }
}