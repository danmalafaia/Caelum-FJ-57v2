package br.com.caelum.twittelumapp.viewmodel

import br.com.caelum.twittelumapp.TwittelumApplication
import br.com.caelum.twittelumapp.bancodedados.TwittelumDatabase

object Injetor {

    private val contexto = TwittelumApplication.getInstance()

    private val database = TwittelumDatabase.getInstance(contexto)

    fun tweetDao() = database.tweetDao()
}