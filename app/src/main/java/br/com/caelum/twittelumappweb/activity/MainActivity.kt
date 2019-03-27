package br.com.caelum.twittelumappweb.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragment.BuscadorDeTweetsFragment
import br.com.caelum.twittelumappweb.fragment.ListaTweetsFragment
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: UsuarioViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(UsuarioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listenerBottomNavigation()

        main_fab.setOnClickListener {
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_sair) {
            viewModel.desloga()
            voltaProLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun voltaProLogin() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun listenerBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_tweets -> {
                    exibe(ListaTweetsFragment())
                    true
                }
                R.id.menu_busca -> {
                    exibe(BuscadorDeTweetsFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun exibe(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_principal, fragment)
        transaction.commit()
    }
}
