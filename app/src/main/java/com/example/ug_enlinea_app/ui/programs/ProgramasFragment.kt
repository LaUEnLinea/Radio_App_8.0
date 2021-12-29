package com.example.ug_enlinea_app.ui.programs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.ug_enlinea_app.R
import com.example.ug_enlinea_app.databinding.FragmentProgramasBinding

class ProgramasFragment : Fragment() {

    private var _binding: FragmentProgramasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgramasBinding.inflate(inflater, container, false)
        val root: View = binding.root


        Animaciones()
        return root
    }

    fun Animaciones(){
        val animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in)

        //Nuestro programas
        // Tertulia
        binding.PlayTertulia.startAnimation(animation)
        binding.PlayTextTertulia.startAnimation(animation)
        // Cartas sobre la mesa
        binding.PlayCartasSM.startAnimation(animation)
        binding.PlayTextCartasSM.startAnimation(animation)
        //En linea con la u
        binding.PlayEnLinU.startAnimation(animation)
        binding.PlayTextEnLinU.startAnimation(animation)
        //Mas de ti
        binding.PlayMasDeTi.startAnimation(animation)
        binding.PlayTextMasDeTi.startAnimation(animation)
        //Turismo
        binding.PlayTurismo.startAnimation(animation)
        binding.PlayTextTurismo.startAnimation(animation)
        //UGTalentShow
        binding.Playugtalentshow.startAnimation(animation)
        binding.PlayTextugtalentshow.startAnimation(animation)
        //Hola emprendedores
        binding.PlayholaEmprendedores.startAnimation(animation)
        binding.PlayTextholaEmprendedores.startAnimation(animation)
        //Hola emprendedores
        binding.Playcafedelaciencia.startAnimation(animation)
        binding.PlayTextcafedelaciencia.startAnimation(animation)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}