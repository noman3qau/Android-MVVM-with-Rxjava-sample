package com.noman.mvvm.viewmodel

import com.noman.mvvm.model.UserModel
import com.noman.mvvm.model.data.User
import com.noman.mvvm.viewmodel.data.UsersList
import timber.log.Timber
import java.util.*
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class UserListViewModel(val userModel: UserModel) {


    fun getUsers(): Observable<UsersList> {

        return userModel.getUsers()
                .debounce(400, TimeUnit.MICROSECONDS)
                .map {
                    Timber.d("Mapping user to UIData...")
                    UsersList(it.take(10), "Top 10 Users")
                }.onErrorReturn {
                    UsersList(emptyList(), "An error occurred", it)
                }

    }

}