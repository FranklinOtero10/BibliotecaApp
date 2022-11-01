package com.example.bibliotecaapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bibliotecaapp.Adapters.PublicacionAdapter
import com.example.bibliotecaapp.MainActivity.Companion.publicacionRepository
import com.example.bibliotecaapp.databinding.ActivityMostrarListaActivityBinding

class MostrarListaActivity : AppCompatActivity() {

    // Variable para configurar viewBinding
    private lateinit var binding: ActivityMostrarListaActivityBinding
    // Variables necesarias para configurar el recyclerview
    private lateinit var recyclerView: RecyclerView
    private lateinit var publicacionAdapter: PublicacionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Lista de publicaciones"
        // Configuracion de viewBinding
        binding = ActivityMostrarListaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Habilitar action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Validar si la lista esta vacia
        if(publicacionRepository.get().size == 0){
            AlertDialog.Builder(this).setTitle(this.resources.getString(R.string.titulo_lista_vacia))
                .setMessage(this.resources.getString(R.string.msg_lista_vacia))
                .setPositiveButton(android.R.string.ok,
                    DialogInterface.OnClickListener
                    { dialogInterface, i ->
                        finish()
                    }).show()
        } else {
            //Utilizando el swipeRefreshLayout
            // Accediendo al evente que actualiza la lista
            binding.swRefresh.setOnRefreshListener {
                // Configurar RecyclerView
                configRecyclerView()

                // Configurando hilo, para asignar tiempo
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.swRefresh.isRefreshing = false
                }, 2000)
                Toast.makeText(this, "Lista actualizada", Toast.LENGTH_SHORT).show()
            }
        }
        configRecyclerView()
    }
    // Método que configura el recyclerview
    private fun configRecyclerView(){
        recyclerView = binding.rcPublicaciones
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        publicacionAdapter = PublicacionAdapter(publicacionRepository.get(), this)
        recyclerView.adapter = publicacionAdapter
    }

    // Método que configura el action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // Finaliza la actividad
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
