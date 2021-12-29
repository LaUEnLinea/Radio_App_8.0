package com.example.ug_enlinea_app.servicios

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.example.ug_enlinea_app.MainActivity
import com.example.ug_enlinea_app.R

class ServicioMusical: Service() {
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private var CHANNEL_ID = "channelID2"
    private val TAG:String="Servicio_musical"
    private lateinit var notificationPlayer: Notification

    init {
        Log.d(TAG, "Servicio Corriendo")
    }

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "Servicio Creado")
    }

    override fun onDestroy() {

        simpleExoPlayer.stop()
        super.onDestroy()
        Log.d(TAG, "Servicio Eliminado")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int ): Int {
        initplayer()
        Log.d(TAG, "Reproduciendo")
        createNotificationChannel()
        val intentHome = Intent(this,MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0,intentHome,0)

        notificationPlayer = NotificationCompat.Builder(this,CHANNEL_ID).also {
            it.setContentTitle("LA U EN LINEA")
            it.setContentText("Estas escuchando... La U En linea")
            it.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            it.setContentIntent(pendingIntent)
            it.setColor(ContextCompat.getColor(this, R.color.azul))
            it.setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.smallicon), 200, 128, false))
            it.setSmallIcon(R.drawable.la_u_en_linea)
            it.addAction(R.drawable.exo_controls_pause,"Pause", pendingIntent)
            it.setStyle(
                androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0)
            )
            it.setPriority(NotificationCompat.PRIORITY_HIGH)

        }.build()

        startForeground(110, notificationPlayer)
        return START_STICKY
    }

    fun initplayer() {


        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        val datasourcefactory: DataSource.Factory =
            DefaultDataSourceFactory(this, Util.getUserAgent(this, "La U en linea"))
        val audiosource: MediaSource =
            ProgressiveMediaSource.Factory(datasourcefactory).createMediaSource(Uri.parse("https://radio.cedia.org.ec/ug-facso"))
        simpleExoPlayer.prepare(audiosource)
        simpleExoPlayer.setPlayWhenReady(true)

    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val chanell = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                lightColor= Color.BLUE
                enableLights(true)
            }
            // Register the channel with the system
            val manager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(chanell)

        }
    }

    override fun onBind(intent: Intent?): IBinder?= null

}