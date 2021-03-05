package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel: ViewModel() {
    private val _word = MutableLiveData<String>()
    val word:LiveData<String>
    get() = _word

    // The current score
   private val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish:LiveData<Boolean>
    get() = _eventGameFinish

    private val _currentTime = MutableLiveData<Long>()
    val currentTime :LiveData<Long>
    get() = _currentTime

    val currentTimeString = Transformations.map(currentTime){time-> DateUtils.formatElapsedTime(time)}

    private val timer :CountDownTimer

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init{
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        _word.value =""
        _score.value=0
        timer = object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
               _currentTime.value = millisUntilFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value= DONE
                onGameFinish()
            }
        }
        timer.start()
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**

     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
           resetList()

        }
        else{
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }
     fun onSkip() {
        if (wordList.isNotEmpty()) {
            _score.value = (score.value)?.minus(1)
        }
        nextWord()
    }

     fun onCorrect() {
        if (wordList.isNotEmpty()) {
            _score.value = (score.value)?.plus(1)
        }
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
        timer.cancel()
    }
    fun onGameFinish(){
        _eventGameFinish.value=true
    }
    fun onGameFinishComplete(){
        _eventGameFinish.value=false
    }
    companion object{
        private const val DONE = 0L
        private const val ONE_SECOND =1000L
        private const val COUNTDOWN_TIME = 60000L
    }
}