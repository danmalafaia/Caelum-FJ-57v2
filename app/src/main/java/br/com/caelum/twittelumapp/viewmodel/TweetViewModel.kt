package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumapp.bancodedados.TweetRepository
import br.com.caelum.twittelumapp.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    fun lista() = repository.lista()

    fun salva(tweet: Tweet) = repository.salva(tweet)

    fun deleta(tweet: Tweet) = repository.deleta(tweet)

}