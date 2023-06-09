package com.example.gametask.data.firebase

import com.example.gametask.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject

interface RemoteConfigManager {
    suspend fun getConfig(onSuccess: (config: Config) -> Unit, onFailure: () -> Unit)
    class Base @Inject constructor(private val preferences: SharedPreferencesDataSource) :
        RemoteConfigManager {
        override suspend fun getConfig(onSuccess: (config: Config) -> Unit, onFailure: () -> Unit) {
            val remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 10
            }

            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.rc_defaults)


            remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val link = remoteConfig.getString(LINK)
                    val status = remoteConfig.getBoolean(STATUS)
                    val linkPref = preferences.getLink()
                    val config = if (linkPref != SharedPreferencesDataSource.Base.DEFAULT) {
                        if (linkPref != link) {
                            preferences.putLink(link)
                        }
                        Config(link, true)
                    } else {
                        preferences.putLink(link)
                        Config(link, status)
                    }
                    onSuccess(config)

                } else {
                    onFailure()
                }
            }

        }

        companion object{
            private const val LINK = "link"
            private const val STATUS = "status"
        }

    }
}