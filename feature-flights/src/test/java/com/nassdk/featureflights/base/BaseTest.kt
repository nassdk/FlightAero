package com.nassdk.featureflights.base

import com.nassdk.corecommon.coroutines.CoroutinesDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule val mainCoroutineRule = MainCoroutineRule()

    protected val dispatcherProvider = CoroutinesDispatcherProvider(
        main = testCoroutineDispatcher,
        default = testCoroutineDispatcher,
        io = testCoroutineDispatcher
    )
}