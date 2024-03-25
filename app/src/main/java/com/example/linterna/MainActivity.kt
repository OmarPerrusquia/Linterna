package com.example.linterna

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isFlashlightOn: Boolean = false
    private lateinit var cameraManager: CameraManager
    private lateinit var flashlightImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashlightImageView = findViewById(R.id.Linterna)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        flashlightImageView.setOnClickListener {
            toggleFlashlight()
        }
    }

    private fun toggleFlashlight() {
        try {
            val cameraId = cameraManager.cameraIdList[0]
            if (!isFlashlightOn) {
                cameraManager.setTorchMode(cameraId, true)
                isFlashlightOn = true
                // Aquí puedes cambiar la imagen de la linterna a encendida si lo deseas
            } else {
                cameraManager.setTorchMode(cameraId, false)
                isFlashlightOn = false
                // Aquí puedes cambiar la imagen de la linterna a apagada si lo deseas
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }
}
