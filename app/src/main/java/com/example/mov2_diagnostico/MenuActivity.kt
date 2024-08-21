package com.example.mov2_diagnostico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class MenuActivity : AppCompatActivity() {

    private lateinit var objFerreteria: Ferreteria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val toolbar: Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)

        objFerreteria = Ferreteria()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_despegable, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent?
        when(item.itemId){
            R.id.itmFormulario -> {
                intent = Intent(applicationContext, FormularioActivity::class.java)
                startActivity(intent)
            }
            R.id.itmConsultar -> {
                val comprasList = FormularioActivity.compras.filterNotNull().map { compra ->
                    "Nombre: ${compra.nombre}, Tel: ${compra.telefono}, Producto: ${compra.producto}, Cantidad: ${compra.cantidad}, Precio: ${compra.precio}"
                }
                intent = Intent(applicationContext, ConsultarActivity::class.java)
                intent.putStringArrayListExtra("comprasList", ArrayList(comprasList))
                startActivity(intent)
            }
            R.id.itmSalir -> {
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}