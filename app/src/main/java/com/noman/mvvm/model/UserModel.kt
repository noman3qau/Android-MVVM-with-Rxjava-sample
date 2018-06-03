package com.noman.mvvm.model

import com.noman.mvvm.model.api.ApiMethods
import com.noman.mvvm.model.data.User
import com.noman.mvvm.model.db.UserDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class UserModel(val apiMethods: ApiMethods, val userDao: UserDao) {


    fun getUsers(): Observable<List<User>> {
        return Observable.concatArray(
                getUsersFromDatabase(),
                getUsersFromApi())
    }

    private fun getUsersFromDatabase(): Observable<List<User>> {

        return userDao.getUsers().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} users from DB..")
                }

    }

    private fun getUsersFromApi(): Observable<List<User>> {

        return apiMethods.getUsers()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} users from Api")
                    storeUsersInDb(it)
                }

    }

    private fun storeUsersInDb(users: List<User>) {

        Observable.fromCallable { userDao.insertAll(users) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${users.size} users from Api in DB...")
                }

    }

}