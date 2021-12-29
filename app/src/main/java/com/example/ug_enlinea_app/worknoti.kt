package com.example.ug_enlinea_app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

class worknoti(context: Context, workerParameters: WorkerParameters):
    Worker(context,workerParameters) {
    override fun doWork(): Result {
        val titulo = inputData.getString("titulo")
        val detalle = inputData.getString("detalle")
        val id = inputData.getLong("idnoti", 0).toInt()
        oreo(titulo,detalle)
        return Result.success()
    }

    companion object{
        fun GuardarNoti(duracion: Long, data: Data?, tag:String?){
            val noti = OneTimeWorkRequest.Builder(worknoti::class.java)
                .setInitialDelay(duracion, TimeUnit.MILLISECONDS).addTag(tag!!)
                .setInputData(data!!).build()
            val instance = WorkManager.getInstance()
            instance.enqueue(noti)
        }
    }

    private fun oreo(t: String?, d: String?){
        val id = "mensaje"
        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(applicationContext, id)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val nc = NotificationChannel(id,"Programas", NotificationManager.IMPORTANCE_HIGH)
            nc.description = "Notification de Programas"
            nc.setShowBadge(true)
            nm.createNotificationChannel(nc)
        }

        val uri: Uri = Uri.parse("https://www.facebook.com/lauenlinea")
        val intentfb = Intent(Intent.ACTION_VIEW, uri)
        val pendingIntentfb = PendingIntent.getActivity(applicationContext, 0, intentfb, PendingIntent.FLAG_ONE_SHOT)
        builder.setAutoCancel(true)
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        builder.setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setContentTitle("EN VIVO: " + t)
            .setTicker("Nueva notificacion")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getApplicationContext().resources, R.drawable.envivoconlau), 200, 128, false))
            .setSmallIcon(R.drawable.logo_u)
            .setContentText(d)
            .setContentIntent(pendingIntent)
            .setContentInfo("nuevo")
            .addAction(
                R.drawable.ic_logofb, "Facebook live",
                pendingIntentfb)
            .addAction(
                R.drawable.ic_home, "Entrar app",
                pendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        val random = Random()
        val idNotify = random.nextInt(8000)
        assert(nm != null)
        nm.notify(idNotify, builder.build())
    }

}