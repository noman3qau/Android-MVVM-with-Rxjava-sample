package com.noman.mvvm.model.api

import com.noman.mvvm.model.data.User
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface ApiMethods {

    @GET("6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
    fun getUsers(): Observable<List<User>>

}