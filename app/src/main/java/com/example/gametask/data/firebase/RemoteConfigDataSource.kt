package com.example.gametask.data.firebase

import android.util.Log
import com.example.gametask.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject

interface RemoteConfigDataSource {
    suspend fun getConfig():Config
    class Base @Inject constructor():RemoteConfigDataSource{
        override suspend fun getConfig(): Config {
            val remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 10
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.rc_defaults)
            remoteConfig.fetchAndActivate().addOnCompleteListener{task->
                if (task.isSuccessful){
                    Log.d("Success","Param updated")
                } else{
                    Log.d("Error","Param failed")
                }
            }
            return Config(remoteConfig.getString("link"),remoteConfig.getBoolean("status") )
        }

    }
}