/*
 * Copyright 2019 Wai Yan - (09 97777 3 444).
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.kyats.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Response
import wyp.kyats.R
import wyp.kyats.cache.centralbank.CurrenciesSaver
import wyp.kyats.cache.centralbank.CurrenciesStorage
import wyp.kyats.component.ui.BaseActivity
import wyp.kyats.component.ui.SnackBar
import wyp.kyats.component.util.Logger
import wyp.kyats.domain.centralbank.ApiUtils
import wyp.kyats.domain.centralbank.response.CurrenciesResponseModel

class SplashActivity : BaseActivity() {

    private lateinit var noInternetSnackBar: SnackBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* imageView.animation = AnimationUtils.loadAnimation(this, R.anim.rotate) */

        noInternetSnackBar = SnackBar(findViewById(android.R.id.content), getString(R.string.INTERNET_CONNECTION_ERROR), Snackbar.LENGTH_INDEFINITE, R.drawable.ic_no_internet, Color.parseColor("#D32F2F"), null)
        this.postOnCreate()
    }

    override fun getLayoutFileId(): Int {
        return R.layout.activity_splash
    }

    private fun postOnCreate() {
        if (CurrenciesStorage.getInstance().isCredentialsAvailable) {
            Logger.d(this.javaClass, "Currencies are already exist.")
            Handler().postDelayed({ this.goToHome() }, 500)
        } else {
            this.getCurrencies()
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun getCurrencies() {

        ApiUtils.getAPIService().currencies.enqueue(object : retrofit2.Callback<CurrenciesResponseModel> {

            override fun onResponse(call: Call<CurrenciesResponseModel>, responseModel: Response<CurrenciesResponseModel>) {

                val currenciesResponse = responseModel.body()
                if (currenciesResponse != null) {
                    Logger.d(this.javaClass, "RESPONSE INFO :" + currenciesResponse.info)
                    CurrenciesSaver(currenciesResponse).save()
                }
                Handler().postDelayed({ goToHome() }, 500)
            }

            override fun onFailure(call: Call<CurrenciesResponseModel>, t: Throwable) {

                Logger.d(this.javaClass, "Error :$t")
            }
        })
    }

    override fun onInternetAvailable() {
        super.onInternetAvailable()
        this.noInternetSnackBar.hide()
        if (!CurrenciesStorage.getInstance().isCredentialsAvailable) {
            this.getCurrencies()
        }
    }

    override fun onInternetUnavailable() {
        super.onInternetUnavailable()
        this.noInternetSnackBar.show()
    }
}
