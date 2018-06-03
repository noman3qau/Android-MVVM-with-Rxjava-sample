package com.noman.mvvm

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import com.noman.mvvm.model.UserModel
import com.noman.mvvm.model.api.ApiMethods
import com.noman.mvvm.model.db.AppDatabase
import com.noman.mvvm.view.UserListFragment
import com.noman.mvvm.viewmodel.UserListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MvvmApp : Application() {

    // For now for sake of simplicitty we now use this intead of dagger
    companion object {
        private var baseUrl: String = "https://randomapi.com/api/"

        private lateinit var retrofit: Retrofit
        private lateinit var apiMethods: ApiMethods
        private lateinit var userModel: UserModel
        private lateinit var userListViewModel: UserListViewModel
        private lateinit var appDatabase: AppDatabase

        fun injectUserApi() = apiMethods
        fun injectUserListViewModel() = userListViewModel
        fun injectUserDao() = appDatabase.userDao()
    }

    override fun onCreate() {
        super.onCreate()

        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())


        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()

        apiMethods = retrofit.create(ApiMethods::class.java)

        appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "mvvm-database").build()

        userModel = UserModel(apiMethods, appDatabase.userDao())

        userListViewModel = UserListViewModel(userModel)


    }


}