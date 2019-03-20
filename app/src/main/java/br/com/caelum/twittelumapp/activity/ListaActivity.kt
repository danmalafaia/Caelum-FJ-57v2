package br.com.caelum.twittelumapp.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import br.com.caelum.twittelumapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        viewModel.lista().observe(this, observer())

        fab_add.setOnClickListener {
            val intencao = Intent(this, TweetActivity::class.java)
            startActivity(intencao)
        }

        val listener = AdapterView.OnItemClickListener { adapter, item, posicao, id ->

            val tweet = lista_tweet.getItemAtPosition(posicao) as Tweet
            perguntaSePrecisaDeletarEsse(tweet)
        }

        lista_tweet.onItemClickListener = listener
    }

    private fun observer(): Observer<List<Tweet>> {
        return Observer {
            lista_tweet.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
        }
    }

    private fun perguntaSePrecisaDeletarEsse(tweet: Tweet) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Deseja mesmo apagar esse tweet?")
        dialog.setTitle("Atenção")
        dialog.setPositiveButton("Sim") { _: DialogInterface, _: Int -> viewModel.deleta(tweet) }
        dialog.setNegativeButton("Não", null)
        dialog.show()
    }

}