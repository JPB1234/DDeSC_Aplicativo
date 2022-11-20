package com.example.ddesc_aplicativo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ddesc_aplicativo.PrincipalActivity.Companion.RESULT
import me.dm7.barcodescanner.zxing.ZXingScannerView
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import com.example.ddesc_aplicativo.databinding.ActivityTelaLeitorCameraBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.core.CameraUtils

class TelaLeitorCamera : AppCompatActivity(), ZXingScannerView.ResultHandler{

    var scannerView : ZXingScannerView? = null

    val REQUEST_CODE_CAMERA = 182 /*inteiro aleatorio*/

    private lateinit var binding: ActivityTelaLeitorCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)

        setPermission()
    }

    override fun handleResult(rawResult: Result?) {
        val intent = Intent(applicationContext, PrincipalActivity::class.java)
        intent.putExtra(RESULT, rawResult.toString())
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        scannerView?.setResultHandler(this)
        scannerView?.startCamera()
    }

    override fun onStop() {
        super.onStop()

        scannerView?.stopCamera()
        onBackPressed()
    }

    private fun setPermission(){
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
        1)
    }

    private fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            1 -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(applicationContext,
                        "A permissão de uso de câmera é necessária para que o aplicativo funcione",
                    Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}