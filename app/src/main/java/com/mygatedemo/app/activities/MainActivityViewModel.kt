package com.mygatedemo.app.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mygatedemo.app.data.User
import com.mygatedemo.app.repositories.UserRepository
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class MainActivityViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var userResult: MutableLiveData<List<User>> = MutableLiveData()
    private var userError: MutableLiveData<String> = MutableLiveData()
    private var userLoader: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var disposableObserver: DisposableObserver<List<User>>

    fun userResult(): LiveData<List<User>> {
        return userResult
    }

    fun userError(): LiveData<String> {
        return userError
    }

    fun userLoader(): LiveData<Boolean> {
        return userLoader
    }

    fun getAllUsersFromDb() {

        disposableObserver = object : DisposableObserver<List<User>>() {

            override fun onComplete() {
                Logger.d("User Fetch Successful")
            }

            override fun onNext(t: List<User>) {
                userResult.postValue(t)
                userLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                userError.postValue(e.localizedMessage)
                userLoader.postValue(false)
            }
        }

        userRepository.getUserList()
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(disposableObserver)
    }


    fun saveUserToDb(user: User) {
        userRepository.saveUser(user)
    }


    fun disposeElements() {
        if (!disposableObserver.isDisposed) disposableObserver.dispose()
    }


}
