package com.nassdk.flightaero.scenario

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.nassdk.flightaero.AppActivity
import org.junit.Rule

class OpenAppActivity<ScenarioData>(
    private val tab: HomeTab = HomeTab.MAIN,
) : BaseScenario<ScenarioData>() {

    @get:Rule
    val activityRule = ActivityTestRule(AppActivity::class.java, true, false)

    override val steps: TestContext<ScenarioData>.() -> Unit
        get() = {
            step("запустить HomeActivity без всплывающих окон") {
                activityRule.launchActivity(Intent())
            }
        }

    enum class HomeTab {
        MAIN,
        HISTORY,
        TRANSFERS,
        CHAT,
        ALSO
    }
}