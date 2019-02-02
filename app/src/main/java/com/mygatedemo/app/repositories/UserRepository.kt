package com.mygatedemo.app.repositories

import com.mygatedemo.app.data.User
import com.mygatedemo.app.database.UserDao
import io.reactivex.Observable


class UserRepository(private val userDao: UserDao) {


    fun getUserList() : Observable<List<User>>  {
        return Observable.create {subscriber ->
            subscriber.onNext(userDao.loadUsersFromDb())
            subscriber.onComplete()
        }
    }


    fun saveUser(user: User) {
      userDao.insertUser(user)
    }


}