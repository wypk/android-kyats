package wyp.kyats

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import io.fabric.sdk.android.Fabric
import wyp.kyats.cache.app.AppInfoStorage
import wyp.kyats.cache.centralbank.CurrenciesStorage
import wyp.kyats.cache.centralbank.ExchangeRatesStorage
import wyp.kyats.cache.otherbanks.OtherBanksExchangeRatesStorage

class MyanmarExchangeApplication : MultiDexApplication() {

    override fun onCreate() {

        super.onCreate()
        Fabric.with(this, Crashlytics())
        Fabric.with(this, Answers())

        //For Vector Drawables. Make sure we use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        //Init Storage
        AppInfoStorage.initialize(this)
        CurrenciesStorage.initialize(this)
        ExchangeRatesStorage.initialize(this)
        OtherBanksExchangeRatesStorage.initialize(this)
    }
}
