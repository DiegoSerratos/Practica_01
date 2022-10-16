 package com.example.Actividad11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Actividad11.R
import com.example.Actividad11.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_view_design.*

 class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
     val data = ArrayList<Alumno>()
     val adapter = AlumnoAdapter(this, data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        //Agregar elementos a la lista
        data.add(
            Alumno(
                "Valeria Ramírez",
                "20151212",
                "vramirez@ucol.mx",
                "https://cdn-icons-png.flaticon.com/512/2335/2335114.png"
            )
        )
        data.add(
            Alumno(
                "Juan Barocio",
                "201649856",
                "jbarocio@ucol.mx",
                "https://cdn-icons-png.flaticon.com/512/2922/2922716.png"
            )
        )
        data.add(
            Alumno(
                "Maria Sernas",
                "20164334",
                "msilvia@ucol.mx",
                "https://cdn-icons-png.flaticon.com/512/2922/2922566.png"
            )
        )
        data.add(
            Alumno(
                "Jesus Huerta",
                "20164685",
                "jhuerta@ucol.mx",
                "https://cdn-icons-png.flaticon.com/512/2922/2922716.png"
            )
        )
        data.add(
            Alumno(
                "Pedro Jimenez",
                "20166694",
                "pjimenez@ucol.mx",
                "https://cdn-icons-png.flaticon.com/512/2922/2922716.png"
            )
        )
        data.add(
            Alumno(
                "Joel Cruz",
                "20159834",
                "jcruz@ucol.mx",
                "https://cdn-icons-png.flaticon.com/512/2922/2922716.png"
            )
        )
        // This will pass the ArrayList to our Adapter


        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener(object : AlumnoAdapter.ClickListener {
            override fun onItemClick(view: View, position:Int){
                Toast.makeText(this@MainActivity,"onItemClick ${position}",Toast.LENGTH_LONG).show()
                itemOptionsMenu(position)
            }
        })

        //variable para recibir todos los extras
        val parExtra = intent.extras
        //variables que recibimos todos los extras
        val msje = parExtra?.getString("mensaje")
        val nombre = parExtra?.getString("nombre")
        val cuenta = parExtra?.getString("cuenta")
        val correo = parExtra?.getString("correo")
        val imagen = parExtra?.getString("imagen")
        //Toast.makeText(this,"${nombre}",Toast.LENGTH_LONG).show()
        if(msje=="nuevo"){
            val insertIndex:Int=data.count()
            data.add(insertIndex,
                Alumno(
                    "${nombre}",
                    "${cuenta}",
                    "${correo}",
                    "${imagen}"
                )
            )
            adapter.notifyItemInserted(insertIndex)
        }

        //Click en agregar
        faButton.setOnClickListener{
            val intento2= Intent(this, MainActivityNuevo::class.java)
            //intento2.putExtra("valor1","Hola mundo")
            startActivity(intento2)
        }

    }

     private fun itemOptionsMenu(position: Int) {
         val popupMenu =
             PopupMenu(this,binding.recyclerview[position].findViewById(R.id.textViewOptions))
         popupMenu.inflate(R.menu.options_menu)
//Para cambiarnos de activity
         val intento2 = Intent(this, MainActivityNuevo::class.java)
//Implementar el click en el item
         popupMenu.setOnMenuItemClickListener(object :
             PopupMenu.OnMenuItemClickListener{
             override fun onMenuItemClick(item: MenuItem?): Boolean {
                 when(item?.itemId){
                     R.id.borrar -> {
                         val tmpAlum = data[position]
                         data.remove(tmpAlum)
                         adapter.notifyDataSetChanged()
                         return true
                     }
                     R.id.modificar ->{
//Tomamos los datos del alumno, en la posición de la lista donde hicieron click
                         val nombre = data[position].nombre
                         val cuenta = data[position].cuenta
                         val correo = data[position].correo
                         val image = data[position].image
//En position tengo el indice del elemento en la lista
                         val idAlum: Int = position
                         intento2.putExtra("mensaje","edit")
                         intento2.putExtra("nombre","${nombre}")
                         intento2.putExtra("cuenta","${cuenta}")
                         intento2.putExtra("correo","${correo}")

                         intento2.putExtra("image","${image}")
//Pasamos por extras el idAlum para poder saber cual editar de la lista (ArrayList)
                         intento2.putExtra("idA",idAlum)
                         startActivity(intento2)
                         return true
                     }
                 }
                 return false
             }
         })
         popupMenu.show()
     }
 }