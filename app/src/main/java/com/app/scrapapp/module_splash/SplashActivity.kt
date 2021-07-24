package com.app.scrapapp.module_splash

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.Login
import com.app.scrapapp.module_login.PhoneNumberActivityV3
import com.app.scrapapp.module_order.AddItemActivity
import com.app.scrapapp.test.MainBhangarwaleActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_v3)
        Thread {
            kotlin.run {
                Thread.sleep(5000)
                runOnUiThread(Runnable {
                    when(Login.IsLogin){
                        true->{
                            createShortCutForAddItemIfUserHasLoggedIn()
                            val intentHome : Intent = Intent(this, MainBhangarwaleActivity::class.java)
                            startActivity(intentHome)
                            finish()
                        }
                        false->{
                            createShortCutForAddItemIfUserIsNotLoggedIn()
                            val intentLogin : Intent = Intent(this,PhoneNumberActivityV3::class.java)
                            startActivity(intentLogin)
                            finish()
                        }
                    }
                })
            }
        }.start()
    }
    private fun createShortCutForAddItemIfUserHasLoggedIn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1){
            val intent = Intent(this, AddItemActivity::class.java)
            intent.setAction("LOCATION_SHORTCUT")
            val shortcutManager: ShortcutManager? = getSystemService(ShortcutManager::class.java)
            shortcutManager?.dynamicShortcuts?.clear()
            val shortcut = ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Add Item")
                .setLongLabel("Add Item")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_add_items_v1))
                .setIntent(intent)
                .build()
            shortcutManager?.dynamicShortcuts = Arrays.asList(shortcut)
        }
    }
    private fun createShortCutForAddItemIfUserIsNotLoggedIn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val intent = Intent(this, PhoneNumberActivityV3::class.java)
            intent.setAction("LOCATION_SHORTCUT")
            val shortcutManager: ShortcutManager? = getSystemService(ShortcutManager::class.java)
            shortcutManager?.dynamicShortcuts?.clear()
            val shortcut = ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Add Item")
                .setLongLabel("Add Item")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_add_items_v1))
                .setIntent(intent)
                .build()
            shortcutManager?.dynamicShortcuts = Arrays.asList(shortcut)
        }
    }
}
