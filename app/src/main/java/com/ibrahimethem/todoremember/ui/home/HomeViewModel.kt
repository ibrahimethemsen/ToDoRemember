package com.ibrahimethem.todoremember.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimethem.todoremember.model.quote.Result
import com.ibrahimethem.todoremember.model.weather.uistate.WeatherModel
import com.ibrahimethem.todoremember.repo.quote.QuoteRepository
import com.ibrahimethem.todoremember.repo.weather.WeatherRepository
import com.ibrahimethem.todoremember.util.Resource
import com.ibrahimethem.todoremember.util.extensions.kelvinToCelcius
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatheRepository: WeatherRepository,
    private val quoteRepository: QuoteRepository
) : ViewModel() {
    private val _weatherResponse = MutableLiveData<WeatherModel>()
    val weatherResponse: LiveData<WeatherModel> = _weatherResponse

    private val _quoteResponse = MutableLiveData<Result?>()
    val quoteResponse : LiveData<Result?> = _quoteResponse

    fun getLocationWeather(lat: String, lon: String, apiKey: String, lang: String) {
        viewModelScope.launch {
            val weatheRequest =
                weatheRepository.getLocation(lat = lat, lon = lon, apiKey = apiKey, lang = lang)
            when (weatheRequest) {
                is Resource.Success -> {
                    val temp = weatheRequest.data.main?.temp?.kelvinToCelcius()
                    val description =
                        weatheRequest.data.weather?.get(0)?.description?.replaceFirstChar { it.uppercase() }
                    val weather = iconWeather(weatheRequest.data.weather?.get(0)?.icon.toString())
                    _weatherResponse.value = generateWeatherModel(
                        temp = temp!!,
                        description = description!!,
                        weather = weather
                    )
                }
                is Resource.Error -> {
                    println("hata mesajı ${weatheRequest.errorCode}")
                }
                is Resource.Exception -> {
                    println("hata exception ${weatheRequest.throwable}")
                }
            }

        }
    }
    fun getQuote(){
        viewModelScope.launch {
            val request = quoteRepository.getRandomQuote()
            when(request){
                is Resource.Success -> {
                    _quoteResponse.postValue(request.data)
                }
                is Resource.Exception -> {
                    println("hata quote ${request.throwable}")
                }
                is Resource.Error -> {
                    println("error quote ${request.errorCode} ${request.errorMessage}")
                }
            }
        }
    }

    private fun generateWeatherModel(
        temp: Int,
        description: String,
        weather: String
    ): WeatherModel {
        return WeatherModel(temp, description, weather)
    }

    private fun iconWeather(id: String): String {
        //http://openweathermap.org/img/wn/10d.png
        return "http://openweathermap.org/img/wn/${id}@2x.png"
    }
}




