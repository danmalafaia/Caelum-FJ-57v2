package br.com.caelum.twittelumapp.bancodedados

import br.com.caelum.twittelumapp.modelo.Tweet

class TweetRepository(private val fonteDeDados: TweetDao) {

    fun lista() = fonteDeDados.lista()

    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)

}
