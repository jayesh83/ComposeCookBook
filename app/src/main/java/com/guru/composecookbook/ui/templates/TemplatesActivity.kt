package com.guru.composecookbook.ui.templates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.templates.logins.LoginOnboarding
import com.guru.composecookbook.ui.templates.profile.ProfileScreen
import com.guru.composecookbook.ui.templates.logins.LoginScreen1
import com.guru.composecookbook.ui.templates.onboardings.OnBoardingScreen1
import com.guru.composecookbook.ui.utils.ComingSoon

class TemplatesActivity : AppCompatActivity() {

    private val templateType: String by lazy { intent.getStringExtra(TYPE) ?: "Profiles" }
    private val darkTheme: Boolean by lazy { intent.getBooleanExtra(DARK_THEME, true) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContent {
            ComposeCookBookTheme(darkTheme = darkTheme) {
                TemplateApp(templateType)
            }
        }
    }

    companion object {
        private const val TYPE = "type"
        private const val DARK_THEME = "darkTheme"

        fun newIntent(context: Context, templateType: String, isDarkTheme: Boolean) =
            Intent(context, TemplatesActivity::class.java).apply {
                putExtra(TYPE, templateType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun TemplateApp(templateType: String) {
    when (templateType) {
        "Profiles" -> ProfileScreen()
        "Login" -> LoginOnboarding()
        "On-boarding" -> OnBoardingScreen1 { }
        else -> ComingSoon()
    }
}
