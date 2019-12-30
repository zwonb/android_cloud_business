package com.yidont.cloud.business.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yidont.cloud.business.R
import com.yidont.cloud.business.adapter.PagerAdapter
import com.yidont.cloud.business.ui.home.HomeUIF
import com.yidont.lib.base.BaseUIA
import com.yidont.lib.util.showToast
import com.yidont.login.ui.LoginUIA
import com.yidont.member.ui.MemberMainUIF
import com.yidont.service.ui.ServiceMainUIF
import com.yidont.store.ui.StoreMainUIF
import kotlinx.android.synthetic.main.uia_main.*

/**
 * 主页
 *
 * @author zwonb
 * @date 2019-12-18
 */
class MainUIA : BaseUIA(R.layout.uia_main) {

    override fun initView(savedInstanceState: Bundle?) {
        initPager()
        initBottom()

        fab.setOnClickListener {
            showToast("扫一扫")
            startAct(LoginUIA::class.java)
        }
    }

    private fun initPager() {
        pager.adapter = PagerAdapter(fragments(), supportFragmentManager, lifecycle)
        pager.isUserInputEnabled = false
        pager.offscreenPageLimit = 4
    }

    private fun initBottom() {
        bottom_nav.setOnNavigationItemSelectedListener {
            val pos = when (it.itemId) {
                R.id.home_menu0 -> 0
                R.id.home_menu1 -> 1
                R.id.home_menu2 -> 2
                R.id.home_menu3 -> 3
                else -> 0
            }
            pager.setCurrentItem(pos, false)
            true
        }
    }

    private fun fragments(): List<Fragment> {
        val list = mutableListOf<Fragment>()
        list.add(HomeUIF())
        list.add(MemberMainUIF())
        list.add(ServiceMainUIF())
        list.add(StoreMainUIF())
        return list
    }
}
