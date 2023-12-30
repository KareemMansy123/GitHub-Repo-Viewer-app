package com.example.strarterandroid.network.local_network

import androidx.room.TypeConverter
import com.example.strarterandroid.core.model.Owner
import com.example.strarterandroid.core.model.Reactions
import com.example.strarterandroid.core.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    var gson = Gson()

    @TypeConverter
    fun fromOwner(owner: Owner?): String {
        return gson.toJson(owner)
    }

    @TypeConverter
    fun toOwner(ownerString: String?): Owner? {
        if (ownerString == null) return null
        val objectType = object : TypeToken<Owner>() {}.type
        return gson.fromJson(ownerString, objectType)
    }


    @TypeConverter
    fun fromUser(user: User?): String {
        return gson.toJson(user)
    }

    @TypeConverter
    fun toUser(userString: String?): User? {
        if (userString == null) return null
        val objectType = object : TypeToken<User>() {}.type
        return gson.fromJson(userString, objectType)
    }

    @TypeConverter
    fun fromReactions(reactions: Reactions?): String {
        return gson.toJson(reactions)
    }

    @TypeConverter
    fun toReactions(reactionsString: String?): Reactions? {
        if (reactionsString == null) return null
        val objectType = object : TypeToken<Reactions>() {}.type
        return gson.fromJson(reactionsString, objectType)
    }

    @TypeConverter
    fun fromUserList(users: List<User>?): String {
        return gson.toJson(users)
    }

    @TypeConverter
    fun toUserList(usersString: String?): List<User>? {
        if (usersString == null) return null
        val objectType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(usersString, objectType)
    }
}
