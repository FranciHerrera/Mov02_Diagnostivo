package com.example.mov2_diagnostico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var usuario: Spinner
    private lateinit var contrasena: EditText
    private lateinit var ingresar : Button
    private lateinit var salir : Button

    private var usuarios = listOf("Francisco", "Angel", "Eduardo", "Alex", "Axel")
    private var contrasenaPredeterminada = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuario = findViewById(R.id.spnUsuario)
        contrasena = findViewById(R.id.edtContraseña)
        ingresar = findViewById(R.id.btnIngreso)
        salir = findViewById(R.id.btnSalir)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,usuarios)
        usuario.adapter = adapter
        ingresar.setOnClickListener {
            val usuarioSeleccionado = usuario.selectedItem.toString()
            val contrasenaIngresada = contrasena.text.toString()

            if(validarCredenciales(usuarioSeleccionado, contrasenaIngresada)){
                val intent  =Intent(this,MenuActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        salir.setOnClickListener {
            finish()
        }
    }
    private fun validarCredenciales(usuario: String, contrasena: String): Boolean{
        return usuarios.contains(usuario) && contrasena == contrasenaPredeterminada
    }
}