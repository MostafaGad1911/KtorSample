package mostafagad.projects.ktorsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KtorApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}