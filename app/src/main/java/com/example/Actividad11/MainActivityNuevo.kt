package com.example.Actividad11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Actividad11.databinding.ActivityMainNuevoBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_nuevo.*

class MainActivityNuevo : AppCompatActivity()
{
    private lateinit var binding: ActivityMainNuevoBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding= ActivityMainNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parExtra = intent.extras
        binding.btnguardar.setOnClickListener{
            var txtNom = binding.txtNombre.text
            var txtCuen = binding.txtCuenta.text
            var txtEm = binding.txtCorreo.text
            var txtImg = binding.txtImage.text
            val intento1 = Intent(this, MainActivity::class.java)
            intento1.putExtra("mensaje", "nuevo")
            intento1.putExtra("nombre", "${txtNom}")
            intento1.putExtra("cuenta", "${txtCuen}")
            intento1.putExtra("correo", "${txtEm}")
            intento1.putExtra("imagen", "${txtImg}")
            startActivity(intento1)
        }
    }
}