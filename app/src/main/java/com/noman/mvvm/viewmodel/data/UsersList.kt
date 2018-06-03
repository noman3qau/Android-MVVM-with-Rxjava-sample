package com.noman.mvvm.viewmodel.data

import com.noman.mvvm.model.data.User

data class UsersList(val users: List<User>, val message: String, val error: Throwable? = null)