package com.example.bibliotecaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.bibliotecaapp.Models.UsuarioEntity
import com.example.bibliotecaapp.databinding.ActivityRegistrarUsuarioBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RegistrarUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "Registrar Usuario"

        binding.btnSave.setOnClickListener {
            if(binding.edtUserName.text.toString().isNotEmpty() && binding.edtPassword.text.toString().isNotEmpty()){
                agregarUsuario()
                Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show()
                //configSharedPreference()
                finish()
            } else {
                binding.edtUserName.error = "Campo requerido"
                binding.edtPassword.error = "Campo requerido"
            }
        }
    }

    private fun agregarUsuario(){
        doAsync {
            BibliotecaApplication.database.usuarioDao().addUsuario(
                UsuarioEntity(
                    nombreUsuario = binding.edtUserName.text.toString(),
                    contraUsuario = binding.edtPassword.text.toString()
                ))
            uiThread {
                finish()
            }
        }
    }

    private fun configSharedPreference(){
        val preferences = getSharedPreferences("Users", MODE_PRIVATE)

        with(preferences.edit()){
            putString("username", binding.edtUserName.text.toString())
            putString("password", binding.edtPassword.text.toString())
                .apply()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Finalizar la actividad
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}