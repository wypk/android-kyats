package wyp.kyats.ui.activity

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import wyp.kyats.R.*
import wyp.kyats.component.ui.BaseActivity
import wyp.kyats.component.ui.CustomTypefaceSpan
import wyp.kyats.component.ui.SnackBar
import wyp.kyats.foundation.playStorePreUrl
import wyp.kyats.ui.fragment.AboutFragment
import wyp.kyats.ui.fragment.CentralBankExchangeRateFragment
import wyp.kyats.ui.fragment.OtherBanksFragment
import wyp.kyats.ui.fragment.SettingsFragment


class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var noInternetSnackBar: SnackBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, string.navigation_drawer_open, string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        //Enable original icon color
        nav_view.itemIconTintList = null

        //Custom Font Style In Menu
        val menu = nav_view.menu
        for (i in 0 until menu.size()) {
            val mi = menu.getItem(i)
            //for applying a font to subMenu ...
            val subMenu = mi.subMenu
            if (subMenu != null && subMenu.size() > 0) {
                for (j in 0 until subMenu.size()) {
                    val subMenuItem = subMenu.getItem(j)
                    applyFontToMenuItem(subMenuItem)
                }
            }
            //the method we have create in activity
            applyFontToMenuItem(mi)
        }

        //Initial View
        supportActionBar!!.title = getString(string.nav_item__central_bank_exchange_rate)
        this.loadFragment(CentralBankExchangeRateFragment())
        toolbar.applyToolbarFont()

        noInternetSnackBar = SnackBar(findViewById(android.R.id.content), getString(string.INTERNET_CONNECTION_ERROR), Snackbar.LENGTH_INDEFINITE, drawable.ic_no_internet, Color.parseColor("#D32F2F"), null)
    }

    override fun getLayoutFileId(): Int {
        return layout.activity_home
    }

    override fun onStart() {
        super.onStart()
        this.randomBackground()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            id.nav_central_bank_exchange -> {
                supportActionBar!!.title = getString(string.nav_item__central_bank_exchange_rate)
                this.loadFragment(CentralBankExchangeRateFragment())
            }
            id.nav_other_banks_exchange -> {
                supportActionBar!!.title = getString(string.nav_item__other_banks_exchange_rate)
                this.loadFragment(OtherBanksFragment())
            }
            id.nav_settings -> {
                supportActionBar!!.title = getString(string.title_fragment_settings)
                this.loadFragment(SettingsFragment())
            }
            id.nav_about -> {
                supportActionBar!!.title = getString(string.title_fragment_about)
                this.loadFragment(AboutFragment())
            }
            id.nav_share -> {
                this.shareApp()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onInternetAvailable() {
        super.onInternetAvailable()
        noInternetSnackBar.hide()
    }

    override fun onInternetUnavailable() {
        super.onInternetUnavailable()
        noInternetSnackBar.show()
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(id.frame_container, fragment)
        fragmentTransaction.commit()
    }

    private fun randomBackground() {

        val backgroundImages = intArrayOf(
                drawable.pic_banner1,
                drawable.pic_banner2,
                drawable.pic_banner3,
                drawable.pic_banner4,
                drawable.pic_banner_5)

        val index = (Math.random() * (backgroundImages.size)).toInt()

        val navigationView = findViewById<View>(id.nav_view) as NavigationView
        val hView = navigationView.getHeaderView(0)
        val llNavHeader = hView.findViewById(id.llNavHeader) as androidx.appcompat.widget.LinearLayoutCompat

        llNavHeader.setBackgroundResource(backgroundImages[index])
    }

    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, playStorePreUrl + this.packageName)
        shareIntent.type = "text/plain"
        startActivity(Intent.createChooser(shareIntent, "Share with"))
    }

    @TargetApi(Build.VERSION_CODES.P)
    private fun applyFontToMenuItem(mi: MenuItem) {

        val font = Typeface.createFromAsset(assets, "fonts/TitilliumWeb-Bold.ttf")
        val mNewTitle = SpannableString(mi.title)
        mNewTitle.setSpan(CustomTypefaceSpan("", font), 0, mNewTitle.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        mNewTitle.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_LEFT), 0, mNewTitle.length, 0)
        mi.title = mNewTitle
    }

    private fun Toolbar.applyToolbarFont() {

        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view is TextView && view.text == title) {
                view.typeface = Typeface.createFromAsset(view.context.assets, "fonts/TitilliumWeb-Bold.ttf")
                break
            }
        }
    }
}
