package com.example.ug_enlinea_app.ui.inicio

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_inicio.*
import org.json.JSONException
import com.example.ug_enlinea_app.R
import com.example.ug_enlinea_app.databinding.FragmentInicioBinding
import com.example.ug_enlinea_app.servicios.ServicioMusical

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private var flagPlay = true
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        generar_agenda()
        Reproductor()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun Reproductor(){
        val animation = AnimationUtils.loadAnimation(getContext(), R.anim.shaking)
        binding.chicaMusic.startAnimation(animation)
        binding.imageReproductor.setImageResource(R.drawable.pause)
        binding.imagenReproductor.setImageResource(R.drawable.playreproductor)
        if(!isMyServiceRunning(ServicioMusical::class.java)){
            activity?.startService(Intent(activity, ServicioMusical::class.java))
        }
        binding.imageReproductor.setOnClickListener(View.OnClickListener {

            if(flagPlay==true){
                activity?.stopService(Intent(activity, ServicioMusical::class.java))
                binding.imageReproductor.setImageResource(R.drawable.play)
                binding.imagenReproductor.setImageResource(R.drawable.pausereproductor)
                flagPlay=false
                binding.chicaMusic.clearAnimation()

            }
            else if(flagPlay==false){
                activity?.startService(Intent(activity, ServicioMusical::class.java))
                binding.imageReproductor.setImageResource(R.drawable.pause)
                binding.imagenReproductor.setImageResource(R.drawable.playreproductor)
                binding.chicaMusic.startAnimation(animation)
                flagPlay=true
            }
        })
    }

    fun isMyServiceRunning(serviceClass : Class<*> ) : Boolean{
        var manager =    activity?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in  manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name.equals(service.service.className)) {
                return true
            }
        }
        return false
    }

    fun generar_agenda(){
        val animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in)
        binding.tablaAgenda?.removeAllViews()
        var queue= Volley.newRequestQueue(getContext())
        var url="https://lauenlinea.net/configuracion/AppRadioUG/agendaApp.php"

        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        tabla_agenda?.startAnimation(animation)
                        var jsonObject = jsonArray.getJSONObject(i)
                        val registro= LayoutInflater.from(getContext()).inflate(R.layout.table_row_ug,null,false)
                        val colImagen=registro.findViewById<View>(R.id.colImagen) as ImageView
                        val colHorario=registro.findViewById<View>(R.id.colHorario) as TextView
                        val colDescripcion=registro.findViewById<View>(R.id.colDescripcion) as TextView
                        if(jsonObject.getString("programa").equals("tertulia")){
                            colImagen.setImageResource(R.drawable.tertulia_logo)
                        }else if(jsonObject.getString("programa").equals("hola emprendedores")){
                            colImagen.setImageResource(R.drawable.logo_hola_emprendores)
                        }else if(jsonObject.getString("programa").equals("mas de ti")){
                            colImagen.setImageResource(R.drawable.masdeti_logo)
                        }else if(jsonObject.getString("programa").equals("ug talent show")){
                            colImagen.setImageResource(R.drawable.logo_the_ug_talent_show)
                        }else if(jsonObject.getString("programa").equals("cafe de la ciencia")){
                            colImagen.setImageResource(R.drawable.logocafe_de_ciencia)
                        }else if(jsonObject.getString("programa").equals("turismo de mente")){
                            colImagen.setImageResource(R.drawable.logo_turismo_demente)
                        }else if(jsonObject.getString("programa").equals("cartas sobre la mesa")){
                            colImagen.setImageResource(R.drawable.cartasobrelamesa_logo)
                        }else if(jsonObject.getString("programa").equals("en linea con la u")){
                            colImagen.setImageResource(R.drawable.enlineaconlau_logo)
                        }
                        colHorario.text=jsonObject.getString("horario")
                        colDescripcion.text=jsonObject.getString("descripcion")
                        tabla_agenda?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }

            }, Response.ErrorListener { error ->
                Toast.makeText(getContext(),"Error No hay conexión", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
        tabla_agenda?.startAnimation(animation)

    }




}