package com.compose.movir.utils
sealed class Resource<T>(var data : T? = null, var message : String? = null) {
    class Success <T>(data : T) :Resource<T>(data,null)
    class Error <T>(message: String, data : T? = null) :Resource<T>(data,message)

}