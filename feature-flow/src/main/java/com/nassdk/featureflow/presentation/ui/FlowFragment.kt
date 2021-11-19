package com.nassdk.featureflow.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.MultiScreen
import com.github.terrakok.modo.android.multi.MultiStackFragmentImpl
import com.github.terrakok.modo.backToTabRoot
import com.github.terrakok.modo.selectStack
import com.nassdk.featureflow.FlowFeature
import com.nassdk.featureflow.R
import com.nassdk.featureflow.domain.entity.FlowTab
import javax.inject.Inject

internal class FlowFragment : MultiStackFragmentImpl() {

    companion object {
        fun newInstance() = FlowFragment()
    }

    @Inject lateinit var modo: Modo

    override fun onCreate(savedInstanceState: Bundle?) {
        FlowFeature.getComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun createTabView(index: Int, parent: LinearLayout): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.layout_tab, parent, false)
            .apply {
                val item = FlowTab.getBy(position = index)
                findViewById<TextView>(R.id.tabTitle).text = getString(item.titleRes)
                findViewById<ImageView>(R.id.tabImage).setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        item.iconRes
                    )
                )
                setOnClickListener {
                    val currentScreen = modo.state.chain.lastOrNull()
                    if (currentScreen is MultiScreen) {
                        if (currentScreen.selectedStack != index) {
                            modo.selectStack(index)
                        } else {
                            modo.backToTabRoot()
                        }
                    }
                }
            }
    }

    override fun onDestroy() {
        FlowFeature.destroyModuleGraph()
        super.onDestroy()
    }
}