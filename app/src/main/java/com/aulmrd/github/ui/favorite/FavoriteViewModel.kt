package com.aulmrd.github.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aulmrd.github.data.local.FavoriteUser
import com.aulmrd.github.data.local.FavoriteUserDao
import com.aulmrd.github.data.local.UserDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?. favoriteUserDao()
    }
    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return userDao?. getFavoriteUser()
    }
}