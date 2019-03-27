package br.com.caelum.twittelumappweb.data

import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.webclient.TweetWebClient

class TweetRepository(private val client: TweetWebClient) {

    val lista: MutableLiveData<List<Tweet>> = MutableLiveData()
    val excecao: MutableLiveData<Throwable> = MutableLiveData()
    val sucesso: MutableLiveData<Boolean> = MutableLiveData()

    fun salva(tweet: Tweet) = client.salva(tweet, sucesso(), erro())
    fun busca() {
        client.buscaTweets(sucessoParaLista(), erro())
    }

    fun lista() = lista

    private fun erro() = { erro: Throwable ->
        excecao.value = erro
    }

    private fun sucesso() = { tweet: Tweet ->
        sucesso.postValue(true)
    }
    private fun sucessoParaLista() = { tweets: List<Tweet> ->
        lista.postValue(tweets)
    }

}