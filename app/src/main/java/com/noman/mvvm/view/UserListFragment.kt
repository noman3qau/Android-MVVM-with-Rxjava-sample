package com.noman.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.noman.mvvm.MvvmApp
import com.noman.mvvm.R
import com.noman.mvvm.viewmodel.data.UsersList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableBlockingSubscribe.subscribe
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.users_fragment.*
import timber.log.Timber
import io.reactivex.Observable
import java.net.ConnectException

class UserListFragment : MvvmFragment() {

    val userListViewModel = MvvmApp.injectUserListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        subscribe(userListViewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received user model with ${it.users.size} users.")
                    showUsers(it)
                }, {
                    Timber.w(it)
                    showError()
                }))

    }

    private fun showUsers(data: UsersList) {

        if (data.error == null) {
            usersList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, data.users)
        } else if (data.error is ConnectException) {
            Timber.d("No connect, May be inform user that data load from DB.")
        } else {
            showError()
        }


    }

    private fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }


}