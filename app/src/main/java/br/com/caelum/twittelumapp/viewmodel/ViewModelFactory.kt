package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumapp.bancodedados.TweetRepository

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private fun repository() = TweetRepository(Injetor.tweetDao())

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TweetViewModel(repository()) as T
}