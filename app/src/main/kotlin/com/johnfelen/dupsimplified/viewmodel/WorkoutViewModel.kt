package com.johnfelen.dupsimplified.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.johnfelen.dupsimplified.model.repository.WorkoutRepository
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.LiftNames
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.MovementPatterns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(private val workoutRepository: WorkoutRepository): ViewModel() {
    private var selectedPrimaryMovementPattern: MovementPatterns? = null

    private val _selectedWorkout = MutableLiveData<Resource<Workout>>()
    val selectedWorkout = _selectedWorkout.distinctUntilChanged()

    private val _newOneRepMax = MutableLiveData<Resource<Double>>()
    val newOneRepMax = _newOneRepMax.distinctUntilChanged()

    fun getWorkout(movementPattern: MovementPatterns) {
        _selectedWorkout.value = createResource(isLoading = true)

        selectedPrimaryMovementPattern = movementPattern
        viewModelScope.launch(Dispatchers.Default) {
            _selectedWorkout.postValue(createResource(workoutRepository.getWorkout(movementPattern)).also(::println))
        }
    }

    fun updateOneRepMax(liftName: LiftNames, repsInLastSet: Int) {
        _newOneRepMax.value = createResource(isLoading = true)

        viewModelScope.launch(Dispatchers.Default) {
            _newOneRepMax.postValue(createResource(workoutRepository.updateOneRepMax(liftName, repsInLastSet)))

            _selectedWorkout.value?.let { workout ->
                if(workout is Resource.Success && workout.data.lifts.last().liftName == liftName) workoutRepository.completeWorkout(selectedPrimaryMovementPattern!!)
            }
        }
    }
}