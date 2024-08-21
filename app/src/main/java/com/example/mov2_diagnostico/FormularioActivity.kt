package com.example.mov2_diagnostico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast

class FormularioActivity : AppCompatActivity() {

    private lateinit var objFerreteria: Ferreteria

    private lateinit var nombre: EditText
    private lateinit var telefono: EditText
    private lateinit var productos: Spinner
    private lateinit var cantidad: EditText
    private lateinit var precio: EditText

    private lateinit var registrar: ImageButton
    private lateinit var consultar: ImageButton
    private lateinit var limpiar: ImageButton

    companion object {
        val compras = Array<Ferreteria?>(10) { null }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        objFerreteria = Ferreteria()

        nombre = findViewById(R.id.edtNombre)
        telefono = findViewById(R.id.edtTelefono)
        productos = findViewById(R.id.spnProductos)
        cantidad = findViewById(R.id.edtCantidad)
        precio = findViewById(R.id.edtPrecio)

        registrar = findViewById(R.id.btnRegistrar)
        consultar = findViewById(R.id.btnConsultar)
        limpiar = findViewById(R.id.btnBorrar)

        var productosArray = arrayOf("Martillo","Destornillador","Clavos","Tornillos","Costal de cemento","Soldadura para madera")
        productos.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,productosArray)


        limpiar.setOnClickListener {
            nombre.setText("")
            telefono.setText("")
            cantidad.setText("")
            precio.setText("")
        }

        registrar.setOnClickListener {
            val nombreComprador = nombre.text.toString()
            val telefonoComprador = telefono.text.toString().toIntOrNull()
            val productoComprado = productos.selectedItem.toString()
            val cantidadComprada = cantidad.text.toString().toIntOrNull()
            val precioTotal = precio.text.toString().toDoubleOrNull()

            if(nombreComprador.isNotEmpty() && telefonoComprador != null
                && cantidadComprada != null && precioTotal != null){
                var posicion = compras.indexOfFirst { it == null }

                if(posicion != -1){
                    compras[posicion] = Ferreteria(nombreComprador,telefonoComprador, productoComprado,cantidadComprada,precioTotal)
                    Toast.makeText(this, "Compra registrada correctamente en " + posicion+1, Toast.LENGTH_SHORT).show()
                    nombre.setText("")
                    telefono.setText("")
                    cantidad.setText("")
                    precio.setText("")
                }
                else{
                    Toast.makeText(this, "No hay espacio disponible", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        consultar.setOnClickListener {
            val comprasList = compras.filterNotNull().map { compra ->
                "Nombre: ${compra.nombre}, Tel: ${compra.telefono}, Producto: ${compra.producto}, Cantidad: ${compra.cantidad}, Precio: ${compra.precio}"
            }

            val intent = Intent(this, ConsultarActivity::class.java)
            intent.putStringArrayListExtra("comprasList", ArrayList(comprasList))
            startActivity(intent)
        }

    }
}