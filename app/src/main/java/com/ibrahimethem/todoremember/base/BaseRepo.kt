package com.ibrahimethem.todoremember.base

import com.ibrahimethem.todoremember.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepo{
    suspend fun <T> safeApiCall(
        apiToBeCalled : suspend () -> Response<T>
    ) : Resource<T>{
        return withContext(Dispatchers.IO){
            try {
                val respone : Response<T> = apiToBeCalled()
                val body = respone.body()
                if (respone.isSuccessful && body != null){
                    Resource.Success(
                        body
                    )
                }else{
                    Resource.Error(
                        respone.code(),
                        respone.message()
                    )
                }
            }catch (t : Throwable){
                //Throwable-> Exception ve Error super class'i
                Resource.Exception(
                    t
                )
            }
        }
    }
}