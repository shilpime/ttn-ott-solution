package com.ottsolution.demo.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.ottsolution.demo.data.utils.StrictModePermitter.permitDiskReads
import com.ottsolution.demo.domain.repositories.PrefsRepo
import javax.inject.Singleton


@Singleton
class SharedPrefs(private val ctx: Context) : PrefsRepo {


    companion object {
        fun getString(context: Context?, prefKeyCloudenaryUrl: String): Any {
            return ""
        }

        const val PREF_KEY_CLOUDENARY_URL = "PREF_KEY_CLOUDENARY_URL"
        const val PREFS_NAME = "secured_ott_prefs"
    }

    /**
     * @EncryptedSharedPreferences to block the super user from hacking into
     * the shared preferences file and reading sensitive user data
     */
    private val prefs: SharedPreferences by lazy {
        permitDiskReads {
            ctx.getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
        }
    }

}