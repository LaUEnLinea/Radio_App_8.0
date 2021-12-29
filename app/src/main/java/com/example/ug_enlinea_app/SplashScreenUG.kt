package com.example.ug_enlinea_app

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ug_enlinea_app.servicios.ServicioMusical
import kotlinx.android.synthetic.main.activity_splash_screen_ug.*

class SplashScreenUG : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_ug)

        if (isNetworkAvailable(this)==false){
            Dialog("NO Tienes acceso a internet","Conectate porfavor")
        }
        if(isMyServiceRunning(ServicioMusical::class.java)){
            startActivity(Intent(this@SplashScreenUG,MainActivity::class.java))
            finish()
        }
        //Añadir ruta de video
        val videoPath = "android.resource://$packageName/raw/logo_animado"
        video_view.setVideoPath(videoPath)
        video_view.setOnCompletionListener {
            val r = object:Runnable{
                override fun run() {
                    StartInicio()

                }
            }
            Handler().postDelayed(r,0)
        }

        video_view.start() // Inicializar
    }

    fun isMyServiceRunning(serviceClass : Class<*> ) : Boolean{
        var manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in  manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name.equals(service.service.className)) {
                return true
            }
        }
        return false
    }


    fun StartInicio(){
        if (isNetworkAvailable(this)==true ){
            startActivity(Intent(this@SplashScreenUG,MainActivity::class.java))
            finish()
        }
    }

    fun Dialog(Titulo:String,Mensajes:String){
        val dialog = AlertDialog.Builder(this)
            .setTitle(Titulo)
            .setMessage(Mensajes)
            .setNegativeButton("Cancelar") { view, _ ->
                Toast.makeText(this, "Aplicacion Cerrada", Toast.LENGTH_SHORT).show()
                this.finishAffinity()

                view.dismiss()
            }
            .setPositiveButton("Reintentar") { view, _ ->
                Toast.makeText(this, "Ok button pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SplashScreenUG,SplashScreenUG::class.java))
                finish ()
                view.dismiss()
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }


    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    // Dialog("Tienes acceso a internet","Trasport_Celulart")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    //Dialog("Tienes acceso a internet","TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    //Dialog("Tienes acceso a internet","TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        Dialog("No tienes acceso a internet","TRANSPORT_ETHERNET")
        return false
    }
}