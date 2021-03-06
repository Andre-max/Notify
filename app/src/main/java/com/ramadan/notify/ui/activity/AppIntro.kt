package com.ramadan.notify.ui.activity


import android.Manifest
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.github.appintro.model.SliderPage
import com.ramadan.notify.R
import com.ramadan.notify.utils.startLoginActivity

class AppIntro : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window!!.navigationBarColor = getColor(R.color.transparent)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        addSlide(
            AppIntroFragment.newInstance(
                SliderPage(
                    "Colorful Notes",
                    imageDrawable = R.drawable.notes,
                    titleTypefaceFontRes = R.font.comfortaa,
                    backgroundColor = getColor(R.color.colorAccent)

                )
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                "Portable drawing board",
                "White and Black Boards",
                titleTypefaceFontRes = R.font.comfortaa,

                imageDrawable = R.drawable.drawing,
                backgroundColor = getColor(R.color.colorAccent)
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                SliderPage(
                    "Record and Listen",
                    " ",
                    imageDrawable = R.drawable.recording,
                    titleTypefaceFontRes = R.font.comfortaa,

                    backgroundColor = getColor(R.color.colorAccent)

                )
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                "Day and Night Theme",
                " ",
                imageDrawable = R.drawable.daynight,
                titleTypefaceFontRes = R.font.comfortaa,

                backgroundColor = getColor(R.color.colorPrimary)
            )
        )

        isColorTransitionsEnabled = true
        setProgressIndicator()
        setIndicatorColor(
            selectedIndicatorColor = getColor(R.color.white),
            unselectedIndicatorColor = getColor(R.color.colorPrimaryDark)
        )
        isVibrate = true
        vibrateDuration = 50L
        setImmersiveMode()
        setTransformer(AppIntroPageTransformerType.Parallax())

        askForPermissions(
            permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            slideNumber = 2,
            required = true
        )
        askForPermissions(
            permissions = arrayOf(
                Manifest.permission.RECORD_AUDIO
            ),

            slideNumber = 3,
            required = true
        )
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)

    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startLoginActivity()
    }

    override fun onUserDeniedPermission(permissionName: String) {
        println("Deny")
        Toast.makeText(this, "MESSAGE", Toast.LENGTH_LONG).show()

    }

    override fun onUserDisabledPermission(permissionName: String) {
        println("Don't ask again")
        Toast.makeText(this, "MMMMM", Toast.LENGTH_LONG).show()
        // User pressed "Deny" + "Don't ask again" on the permission dialog
    }
}