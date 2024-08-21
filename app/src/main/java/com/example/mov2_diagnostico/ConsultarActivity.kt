package com.example.mov2_diagnostico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ConsultarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        val listView: ListView = findViewById(R.id.listViewCompras)

        // Recupera los datos pasados desde FormularioActivity
        val comprasList = intent.getStringArrayListExtra("comprasList")

        // Si no es nulo, setea el adaptador para mostrar los datos
        comprasList?.let {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
            listView.adapter = adapter
        }

    }
}