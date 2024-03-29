package com.example.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.moengage.core.disableDataTracking
import com.moengage.inapp.MoEInAppHelper
import com.moengage.core.enableDataTracking
import com.moengage.inapp.listeners.SelfHandledAvailableListener
import com.moengage.inapp.model.SelfHandledCampaignData
import java.util.logging.Logger.getLogger


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        findViewById<Button>(R.id.button5).setOnClickListener { goBack() }
        findViewById<Button>(R.id.enable).setOnClickListener { enableMoE() }
        findViewById<Button>(R.id.disable).setOnClickListener { disableMoE() }
    }

    fun goBack(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun enableMoE (){
        enableDataTracking(this)
    }

    fun disableMoE (){
        disableDataTracking(this)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYTAG","Second onpause called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYTAG","Second onResume called...")
    }

    override fun onStart() {
        super.onStart()

//        MoEInAppHelper.getInstance().setInAppContext(setOf("Chase1"))
//        MoEInAppHelper.getInstance().getSelfHandledInApp(this, {Log.e("test","test") } )
//        MoEInAppHelper.getInstance().getSelfHandledInApp(this, {Log.e("test","test") } )
//        MoEInAppHelper.getInstance().resetInAppContext()

//
//        MoEInAppHelper.getInstance().getSelfHandledInApp(this, {Log.e("test","test") } )
//        MoEInAppHelper.getInstance().resetInAppContext()

//        MoEInAppHelper.getInstance().showInApp(this)

// Call Banner 1
        MoEInAppHelper.getInstance().setInAppContext(setOf("Chase1"))
        MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner1 ->
            MoEInAppHelper.getInstance().resetInAppContext()
            MoEInAppHelper.getInstance().setInAppContext(setOf("Chase2"))
            Log.e("Banner1","onStart() : onSelfHandledAvailable1() : $banner1")
//
            // Call Banner 2
            MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner2 ->
                MoEInAppHelper.getInstance().resetInAppContext()
                MoEInAppHelper.getInstance().setInAppContext(setOf("Chase3"))
                Log.e("Banner2","onStart() : onSelfHandledAvailable1() : $banner2")
//
                // Call Banner 3
                MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner3->
                    MoEInAppHelper.getInstance().resetInAppContext()
                    Log.e("Banner3","onStart() : onSelfHandledAvailable1() : $banner3")
                }
            }
        }
    }

}