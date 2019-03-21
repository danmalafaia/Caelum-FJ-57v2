package br.com.caelum.twittelumapp.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.extensions.decodificaParaBase64
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import br.com.caelum.twittelumapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tweet.*
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var localFoto: String? = null

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                carregaFoto()
            }
        }
    }

    private fun publicaTweet() {
        val tweet = criaTweet()
        viewModel.salva(tweet)
        Toast.makeText(this, "$tweet foi salvo com sucesso :D", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.tweet_menu_cadastrar -> {
                publicaTweet()
                finish()
            }
            android.R.id.home -> finish()

            R.id.tweet_menu_foto -> {
                tiraFoto()
            }
        }
        return true
    }

    private fun tiraFoto() {
        val vaiPraCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        vaiPraCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivityForResult(vaiPraCamera, 123)
    }

    fun defineLocalDaFoto(): Uri? {
        localFoto = "${getExternalFilesDir("fotos")}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this, "MeuProvider", arquivo)
    }

    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localFoto)
        val bm = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)
        tweet_foto.setImageBitmap(bm)
        val fotoNaBase64 = bm.decodificaParaBase64()
        tweet_foto.tag = fotoNaBase64
        tweet_foto.scaleType = ImageView.ScaleType.FIT_XY
    }

    fun criaTweet(): Tweet {
        val mensagemDoTweet: String = tweet_mensagem.text.toString()
        val foto: String? = tweet_foto.tag as String?
        return Tweet(mensagemDoTweet, foto)
    }
}
