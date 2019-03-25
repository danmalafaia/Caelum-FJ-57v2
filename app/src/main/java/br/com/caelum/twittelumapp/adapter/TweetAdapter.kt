package br.com.caelum.twittelumapp.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter(private val tweets: List<Tweet>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tweet = tweets[position]
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.item_tweet, parent, false)
        view.item_conteudo.text = tweet.mensagem
        tweet.foto?.let {
            view.item_foto.visibility = View.VISIBLE
            view.item_foto.setImageBitmap(Carregador.decodifica(it))
        }
        return view
    }

    override fun getItem(position: Int): Any = tweets[position]

    override fun getItemId(position: Int): Long = tweets[position].id.toLong()

    override fun getCount(): Int = tweets.size
}

object Carregador {
    fun decodifica(foto: String): Bitmap {
        val decode: ByteArray = Base64.decode(foto, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decode, 0, decode.size)
    }
}