package com.example.ug_enlinea_app

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.WorkManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ug_enlinea_app.databinding.ActivityMainBinding
import org.json.JSONException
import java.time.format.DateTimeFormatter
import java.util.*
import com.example.ug_enlinea_app.servicios.ServicioMusical


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var calendar= Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fab.setOnClickListener {
                view ->
            Snackbar.make(view, "Visita nuestras Redes sociales", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            val uri: Uri = Uri.parse("https://lauenlinea.net/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        activarshares()
        crearNotificacionesPrograma()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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


    fun AbrirTerturlia(View: View?) {
        val url = "https://lauenlinea.net/view/programas/tertulia.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun AbrirMásdeti(View: View?) {
        val url = "https://lauenlinea.net/view/programas/masdeti.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
    fun AbrirTurismodemente(View: View?) {
        val url = "https://lauenlinea.net/view/programas/turismodemente.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
    fun AbrirCartaSM(View: View?) {
        val url = "https://lauenlinea.net/view/programas/cartassobrelamesa.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
    fun AbrirEnlineaconlaU(View: View?) {
        val url = "https://lauenlinea.net/view/programas/enlineaconlau.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun AbrirUGtaleteShow(View: View?) {
        val url = "https://lauenlinea.net/view/programas/theugstalentshow.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun AbrirHolaEmprendores(View: View?) {
        val url = "https://lauenlinea.net/view/programas/holaemprendedores.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun cerrarAPP(item: MenuItem?) {
        Toast.makeText(this, "Vuelve pronto,\n Te extrañaremos ♥", Toast.LENGTH_LONG).show()
        stopService(Intent(this, ServicioMusical::class.java))
        this.finish()
    }


    fun AbrirCafeDeCiencia(View: View?){
        val url = "https://lauenlinea.net/view/programas/cafedelaciencia.html"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


    private fun generateKey(): String{
        return UUID.randomUUID().toString()
    }

    private fun EnviarData(titulo:String,detalle:String,id_noti:Int): Data {
        //Toast.makeText(this,"AlarmaCreada", Toast.LENGTH_LONG).show()
        return Data.Builder()
            .putString("titulo",titulo)
            .putString("detalle",detalle)
            .putInt("id_noti",id_noti).build()

    }

    private fun CancelarAlarma(tag: String){
        WorkManager.getInstance(this).cancelAllWork()
        //Toast.makeText(this,"AlarmaCancelada", Toast.LENGTH_LONG).show()
    }

    private fun CancelarAllAlarma(){
        WorkManager.getInstance(this).cancelAllWork()
        //Toast.makeText(this,"AlarmaCancelada", Toast.LENGTH_LONG).show()

    }


    private fun convetirFecha(fecha: String): Calendar {

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        var LocalDateTime = java.time.LocalDateTime.parse(fecha, formatter);
        var year=LocalDateTime.year
        var month=(LocalDateTime.monthValue-1)
        var day=LocalDateTime.dayOfMonth
        var hours=LocalDateTime.hour
        var minutes=LocalDateTime.minute
        var seconds=LocalDateTime.second
        calendar.set(year,month,day,hours,minutes,seconds)

        return calendar
    }


    public fun crearNotificacionesPrograma(){

        var queue= Volley.newRequestQueue(this)
        var url="https://lauenlinea.net/configuracion/AppRadioUG/alarmaApp.php"
        val sharedPrefFile: String=R.string.noti_preference_key.toString()
        val SharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharedCartasSM= SharedPreferences.getString(R.string.noti_CartasSM.toString(),"")
        val sharedEnlineaU=SharedPreferences.getString(R.string.noti_EnLineaConLa.toString(),"")
        val sharedMasDeTi= SharedPreferences.getString(R.string.noti_MasDeTi.toString(),"")
        val sharedTertulia= SharedPreferences.getString(R.string.noti_Tertulia.toString(),"")
        val sharedTurismo=SharedPreferences.getString(R.string.noti_TurismoDeMente.toString(),"")
        val sharedCafe=SharedPreferences.getString(R.string.noti_CafeDeLaCiencia.toString(),"")
        val sharedTalent=SharedPreferences.getString(R.string.noti_UgTalentShow.toString(),"")
        val sharedHolaEmp=SharedPreferences.getString(R.string.noti_HolaEmprededores.toString().toString(),"")

        var jsonObjectRequest= JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    CancelarAllAlarma()
                    for(i in 0 until jsonArray.length()){
                        var jsonObject = jsonArray.getJSONObject(i)
                        var fecha_alarm=jsonObject.getString("fecha_alarma")
                        var programa=jsonObject.getString("programa")
                        var descripcion=jsonObject.getString("descripcion")
                        var fecha_cal= convetirFecha(fecha_alarm)
                        var alertime: Long = fecha_cal.timeInMillis - System.currentTimeMillis()
                        if(alertime>0){
                            val random = (Math.random() * 50 + 1).toInt()
                            if(programa=="Cartas sobre la mesa" && sharedCartasSM=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                // Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }
                            if(programa=="En linea con la u" && sharedEnlineaU=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                // Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }
                            if(programa=="Mas de ti" && sharedMasDeTi=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                // Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }
                            if(programa=="Tertulia" && sharedTertulia=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                //   Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }
                            if(programa=="Turismo de mente" && sharedTurismo=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                // Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }
                            if(programa=="Cafe de la ciencia" && sharedCafe=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                //  Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }
                            if(programa=="Ug talent show" && sharedTalent=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                //   Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }
                            if(programa=="Hola emprendedores" && sharedHolaEmp=="Activado"){
                                val data = EnviarData(programa, descripcion, random)
                                worknoti.GuardarNoti(alertime,data,programa)
                                // Toast.makeText(this,"creado" + "Estado: " + sharedCartasSM + " "+ programa+fecha_cal.time.toString() + "minisegundos"+ alertime, Toast.LENGTH_LONG).show()
                            }


                        }
                        else{
                            // Toast.makeText(this,"Tiempo pasó", Toast.LENGTH_LONG).show()
                        }
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }

            }, Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }



    fun activarshares(){

        val sharedPrefFile: String=R.string.noti_preference_key.toString()
        val SharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val username: String? = SharedPreferences.getString(R.string.noti_CartasSM.toString(),null)
        // Toast.makeText(this,SharedPreferences.getString(R.string.noti_CartasSM.toString(),"").toString(), Toast.LENGTH_LONG).show()
        if(username==null){
            val Activado:String = "Activado"
            val editor: SharedPreferences.Editor =  SharedPreferences.edit()
            editor.putString(R.string.noti_CartasSM.toString(),Activado)
            editor.putString(R.string.noti_EnLineaConLa.toString(),Activado)
            editor.putString(R.string.noti_MasDeTi.toString(),Activado)
            editor.putString(R.string.noti_Tertulia.toString(),Activado)
            editor.putString(R.string.noti_TurismoDeMente.toString(),Activado)
            editor.putString(R.string.noti_CafeDeLaCiencia.toString(),Activado)
            editor.putString(R.string.noti_UgTalentShow.toString(),Activado)
            editor.putString(R.string.noti_HolaEmprededores.toString(),Activado)
            editor.apply()
            editor.commit()
        }

        //  Toast.makeText(this,SharedPreferences.getString(R.string.noti_CartasSM.toString(),"") + SharedPreferences.getString(R.string.noti_EnLineaConLa.toString(),"") +
        //       SharedPreferences.getString(R.string.noti_MasDeTi.toString(),"") +SharedPreferences.getString(R.string.noti_Tertulia.toString(),"") + SharedPreferences.getString(R.string.noti_TurismoDeMente.toString(),"")
        //     +SharedPreferences.getString(R.string.noti_CafeDeLaCiencia.toString(),"") + SharedPreferences.getString(R.string.noti_UgTalentShow.toString(),"") +SharedPreferences.getString(R.string.noti_HolaEmprededores.toString().toString(),""), Toast.LENGTH_LONG).show()
    }
}