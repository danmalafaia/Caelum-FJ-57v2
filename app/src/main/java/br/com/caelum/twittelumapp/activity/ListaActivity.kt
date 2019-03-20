package br.com.caelum.twittelumapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.bancodedados.TwittelumDatabase
import br.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        fab_add.setOnClickListener {
            val intencao = Intent(this, TweetActivity::class.java)
            startActivity(intencao)
        }
    }

    override fun onResume() {
        super.onResume()

        val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        val tweets: List<Tweet> = tweetDao.lista()
        lista_tweet.adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)
    }
}