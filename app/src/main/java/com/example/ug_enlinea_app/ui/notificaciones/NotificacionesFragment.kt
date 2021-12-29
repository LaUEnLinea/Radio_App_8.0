package com.example.ug_enlinea_app.ui.notificaciones

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ug_enlinea_app.MainActivity
import com.example.ug_enlinea_app.R
import com.example.ug_enlinea_app.databinding.FragmentNotificacionesBinding

class NotificacionesFragment : Fragment() {


    var _binding: FragmentNotificacionesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificacionesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        AsignarImagenNotificacion()
        FuncionalidadBotones()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun FuncionalidadBotones(){
        val sharedPrefFile: String=R.string.noti_preference_key.toString()
        val sharedPreferences = activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        //Cartas sobre la mesa
        binding.campanaCartas.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiCSM = sharedPreferences?.getString(R.string.noti_CartasSM.toString(),"")
                if (notiCSM=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_CartasSM.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiCSM=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_CartasSM.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })
        //En linea con la u
        binding.campanaEnlinea.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiEnLineaConLa = sharedPreferences?.getString(R.string.noti_EnLineaConLa.toString(),"")
                if (notiEnLineaConLa=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_EnLineaConLa.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiEnLineaConLa=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_EnLineaConLa.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })
        //Mas de ti
        binding.campanaMas.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiMasDeTi = sharedPreferences?.getString(R.string.noti_MasDeTi.toString(),"")
                if (notiMasDeTi=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_MasDeTi.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiMasDeTi=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_MasDeTi.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })
        //Tertulia
        binding.campanaTertulia.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiTertulia = sharedPreferences?.getString(R.string.noti_Tertulia.toString(),"")
                if (notiTertulia=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_Tertulia.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiTertulia=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_Tertulia.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })
        //Turismo De Mente
        binding.campanaTurismo.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiTurismoDeMente = sharedPreferences?.getString(R.string.noti_TurismoDeMente.toString(),"")
                if (notiTurismoDeMente=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_TurismoDeMente.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiTurismoDeMente=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_TurismoDeMente.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })

// Cafe de ciencia
        binding.campanaCafe.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiCafeDeLaCiencia = sharedPreferences?.getString(R.string.noti_CafeDeLaCiencia.toString(),"")
                if (notiCafeDeLaCiencia=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_CafeDeLaCiencia.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiCafeDeLaCiencia=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_CafeDeLaCiencia.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })
//Talente Show
        binding.campanaTalent.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiUgTalentShow = sharedPreferences?.getString(R.string.noti_UgTalentShow.toString(),"")
                if (notiUgTalentShow=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_UgTalentShow.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiUgTalentShow=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_UgTalentShow.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })
//Hola emprendedores
        binding.campanaHola.setOnClickListener(View.OnClickListener {
            if (sharedPreferences != null) {
                val notiHolaEmprededores = sharedPreferences?.getString(R.string.noti_HolaEmprededores.toString(),"")
                if (notiHolaEmprededores=="Activado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_HolaEmprededores.toString(),"Desactivado")
                        commit()
                    }
                }
                else if (notiHolaEmprededores=="Desactivado"){
                    with (sharedPreferences.edit()) {
                        putString(R.string.noti_HolaEmprededores.toString(),"Activado")
                        commit()
                    }
                }
            }
            AsignarImagenNotificacion()
        })

    }


    fun AsignarImagenNotificacion(){
        val sharedPrefFile: String=R.string.noti_preference_key.toString()
        val sharedPreferences = activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val notiCSM = sharedPreferences?.getString(R.string.noti_CartasSM.toString(),"")
        val notiEnLineaConLa = sharedPreferences?.getString(R.string.noti_EnLineaConLa.toString(),"")
        val notiMasDeTi = sharedPreferences?.getString(R.string.noti_MasDeTi.toString(),"")
        val notiTertulia = sharedPreferences?.getString(R.string.noti_Tertulia.toString(),"")
        val notiTurismoDeMente = sharedPreferences?.getString(R.string.noti_TurismoDeMente.toString(),"")
        val notiCafeDeLaCiencia = sharedPreferences?.getString(R.string.noti_CafeDeLaCiencia.toString(),"")
        val notiUgTalentShow = sharedPreferences?.getString(R.string.noti_UgTalentShow.toString(),"")
        val notiHolaEmprededores = sharedPreferences?.getString(R.string.noti_HolaEmprededores.toString(),"")

        if(notiCSM=="Activado"){
            binding.campanaCartas.setImageResource(R.drawable.activar_campana)
            binding.textCartas.setText("Deshabilitar Notificación")
        }
        else if (notiCSM=="Desactivado"){
            binding.campanaCartas.setImageResource(R.drawable.campana)

            binding.textCartas.setText("Añadir Notificación")
        }
        if(notiEnLineaConLa=="Activado"){
            binding.campanaEnlinea.setImageResource(R.drawable.activar_campana)
            binding.textEnlinea.setText("Deshabilitar Notificación")
        }
        else if (notiEnLineaConLa=="Desactivado"){
            binding.campanaEnlinea.setImageResource(R.drawable.campana)
            binding.textEnlinea.setText("Añadir Notificación")
        }

        if(notiMasDeTi=="Activado"){
            binding.campanaMas.setImageResource(R.drawable.activar_campana)
            binding.textMas.setText("Deshabilitar Notificación")
        }
        else if (notiMasDeTi=="Desactivado"){
            binding.campanaMas.setImageResource(R.drawable.campana)
            binding.textMas.setText("Añadir Notificación")
        }

        if(notiTertulia=="Activado"){
            binding.campanaTertulia.setImageResource(R.drawable.activar_campana)
            binding.textTertulia.setText("Deshabilitar Notificación")
        }
        else if (notiTertulia=="Desactivado"){
            binding.campanaTertulia.setImageResource(R.drawable.campana)
            binding.textTertulia.setText("Añadir Notificación")
        }

        if(notiTurismoDeMente=="Activado"){
            binding.campanaTurismo.setImageResource(R.drawable.activar_campana)
            binding.textTurismo.setText("Deshabilitar Notificación")
        }
        else if (notiTurismoDeMente=="Desactivado"){
            binding.campanaTurismo.setImageResource(R.drawable.campana)
            binding.textTurismo.setText("Añadir Notificación")
        }

        if(notiCafeDeLaCiencia=="Activado"){
            binding.campanaCafe.setImageResource(R.drawable.activar_campana)
            binding.textCafe.setText("Deshabilitar Notificación")
        }
        else if (notiCafeDeLaCiencia=="Desactivado"){
            binding.campanaCafe.setImageResource(R.drawable.campana)
            binding.textCafe.setText("Añadir Notificación")
        }

        if(notiUgTalentShow=="Activado"){
            binding.campanaTalent.setImageResource(R.drawable.activar_campana)
            binding.textTalent.setText("Deshabilitar Notificación")
        }
        else if (notiUgTalentShow=="Desactivado"){
            binding.campanaTalent.setImageResource(R.drawable.campana)
            binding.textTalent.setText("Añadir Notificación")
        }

        if(notiHolaEmprededores=="Activado"){
            binding.campanaHola.setImageResource(R.drawable.activar_campana)
            binding.textHola.setText("Deshabilitar Notificación")
        }
        else if (notiHolaEmprededores=="Desactivado"){
            binding.campanaHola.setImageResource(R.drawable.campana)
            binding.textHola.setText("Añadir Notificación")
        }
        (activity as MainActivity).crearNotificacionesPrograma()

    }
}