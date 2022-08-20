package com.ibrahimethem.todoremember.ui.home


import androidx.lifecycle.*
import com.ibrahimethem.todoremember.domain.model.quote.Result
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import com.ibrahimethem.todoremember.domain.model.weather.uistate.WeatherModel
import com.ibrahimethem.todoremember.domain.usecase.GetAllTodoUseCase
import com.ibrahimethem.todoremember.domain.usecase.GetRandomQuoteUseCase
import com.ibrahimethem.todoremember.domain.usecase.LocationWeatherUseCase
import com.ibrahimethem.todoremember.domain.usecase.UpdateTodoUseCase
import com.ibrahimethem.todoremember.util.Resource
import com.ibrahimethem.todoremember.util.extensions.kelvinToCelcius
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllUseCase : GetAllTodoUseCase,
    private val updatetodo : UpdateTodoUseCase,
    private val locationWeatherUseCase: LocationWeatherUseCase,
    private val randomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {
    private val _weatherResponse = MutableLiveData<WeatherModel>()
    val weatherResponse: LiveData<WeatherModel> = _weatherResponse

    private val _quoteResponse = MutableLiveData<Result?>()
    val quoteResponse : LiveData<Result?> = _quoteResponse

    private val _homeDate = MutableLiveData<String?>()
    val homeDate : LiveData<String?> = _homeDate

    fun getLocationWeather(lat: String, lon: String, apiKey: String, lang: String) {
        viewModelScope.launch {
            val weatheRequest =
                locationWeatherUseCase.invoke(lat = lat, lon = lon, apiKey = apiKey, lang = lang)
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
            when(val request = randomQuoteUseCase.invoke()){
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
    fun getDate() : String{
        val formatter = SimpleDateFormat("dd-MM-EEE", Locale("tr"))
        val calendar = Calendar.getInstance()
        val day = formatter.format(calendar.time.time)
        _homeDate.value = day
        return day
    }

    fun updateTodoCheck(todoRemember: TodoRemember){
        viewModelScope.launch {
            updatetodo.invoke(todoRemember)
        }
    }
    private fun generateWeatherModel(
        temp: Int,
        description: String,
        weather: String
    ): WeatherModel {
        return WeatherModel(temp, description, weather)
    }

    fun getAllTodo(date : String) : Flow<List<TodoRemember>> {
        return getAllUseCase.invoke(date)
    }

    private fun iconWeather(id: String): String {
        //http://openweathermap.org/img/wn/10d.png
        return "http://openweathermap.org/img/wn/${id}@2x.png"
    }

}




