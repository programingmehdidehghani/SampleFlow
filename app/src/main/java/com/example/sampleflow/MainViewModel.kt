package com.example.sampleflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        //emit(startingValue)
        while (currentValue > 0){
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _shareFlow = MutableSharedFlow<Int>()
    val shareFlow = _shareFlow.asSharedFlow()

    init {
        collectFlow()
        squareNumber(3)
        viewModelScope.launch {
            shareFlow.collect{
                delay(2000L)
                println("FLOW: The received number is $it")
            }
        }
        viewModelScope.launch {
            shareFlow.collect{
                delay(3000L)
                println("Second FLOW: The received number is $it")
            }
        }
    }

    fun squareNumber(number : Int){
        viewModelScope.launch {
            _shareFlow.emit(number * number)
        }
    }

    fun incrementCounter(){
        _stateFlow.value += 1
    }

    private fun collectFlow(){
        viewModelScope.launch {
            countDownFlow
                .filter { time ->
                    time % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println(time)
                }
                .collect { time ->
                println("the current time is $time")
            }
        }
    }
}