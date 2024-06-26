package com.projetosrafaelfemina.preferenciasdousuario

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.projetosrafaelfemina.preferenciasdousuario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var color = ""

    companion object {
        const val PREFERENCES_FILE = "ArquivoPreferencias"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutPrincipal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar!!.hide()

        binding.color1.setOnClickListener {
            color = "#009A69"
            toSave(color)
        }

        binding.color2.setOnClickListener {
            color = "#78007C"
            toSave(color)
        }

        binding.color3.setOnClickListener {
            color = "#FF0000"
            toSave(color)
        }

        binding.color4.setOnClickListener {
            color = "#0097FF"
            toSave(color)
        }

    }

    private fun toSave(cor: String) {

        binding.layoutPrincipal.setBackgroundColor(Color.parseColor(cor))

        binding.btChangeBackgroudColor.setOnClickListener { view ->
            val preferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("cor", cor)
            editor.apply()

            snackbar(view)
        }
    }

    private fun snackbar(view: View) {
        val snackbar =
            Snackbar.make(view, "Cor de fundo alterada com sucesso!", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Ok") {

        }
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setBackgroundTint(Color.WHITE)
        snackbar.setTextColor(Color.BLACK)
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()

        val preferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
        val color = preferences.getString("cor", "")

        if (color!!.isNotEmpty()) {
            binding.layoutPrincipal.setBackgroundColor(Color.parseColor(color))
        }
    }

}