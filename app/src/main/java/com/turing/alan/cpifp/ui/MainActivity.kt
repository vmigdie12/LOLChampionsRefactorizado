package com.turing.alan.cpifp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.turing.alan.cpifp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar el fragmento de lista al iniciar la actividad
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChampionListFragment())
                .commit()
        }
    }

    // Función para navegar al fragmento de detalles
    fun navigateToChampionDetail(championId: Int) {
        val fragment = ChampionDetailFragment.newInstance(championId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Permite regresar a la lista con el botón "atrás"
            .commit()
    }
}
