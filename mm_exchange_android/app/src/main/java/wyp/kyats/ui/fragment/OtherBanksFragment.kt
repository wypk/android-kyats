package wyp.kyats.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_other_banks.*
import wyp.kyats.R.layout
import wyp.kyats.R.string
import wyp.kyats.component.ui.BaseFragment
import wyp.kyats.ui.adapter.OtherBanksViewPagerAdapter


class OtherBanksFragment : BaseFragment() {

    override fun getLayoutXmlId() = layout.fragment_other_banks

    override fun createView() {
        //init
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setupViewPager(otherBanksViewPager)
        tabLayout.setupWithViewPager(otherBanksViewPager)
        this.setCustomFont()
    }

    private fun setupViewPager(viewPager: ViewPager) {

        val adapter = OtherBanksViewPagerAdapter(childFragmentManager)
        adapter.addFragment(KBZBankExchangeRateFragment(), getString(string.title__kbz_bank_exchange_rate))
        adapter.addFragment(CBBankExchangeRateFragment(), getString(string.title__cb_bank_exchange_rate))
        adapter.addFragment(AYABankExchangeRateFragment(), getString(string.title__aya_bank_exchange_rate))
        viewPager.adapter = adapter
    }

    private fun setCustomFont() {

        val vg = tabLayout.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount

        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup
            val tabChildCounts = vgTab.childCount
            for (i in 0 until tabChildCounts) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    tabViewChild.typeface = Typeface.createFromAsset(view!!.context.assets, "fonts/TitilliumWeb-Bold.ttf")
                }
            }
        }
    }
}
