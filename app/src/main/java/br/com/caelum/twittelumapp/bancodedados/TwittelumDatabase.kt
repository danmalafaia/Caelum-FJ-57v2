package br.com.caelum.twittelumapp.bancodedados

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.caelum.twittelumapp.modelo.Tweet

@Database(entities = [Tweet::class], version = 1,  exportSchema = false)
abstract class TwittelumDatabase : RoomDatabase() {

    abstract fun tweetDao(): TweetDao

    companion object {

        private var database: TwittelumDatabase? = null
        private val DATABASE = "TwittelumDB"

        fun getInstance(context: Context): TwittelumDatabase {
            return database ?: criaBanco(context).also { database = it }
        }

        private fun criaBanco(context: Context): TwittelumDatabase {
            return Room.databaseBuilder(context, TwittelumDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }
}