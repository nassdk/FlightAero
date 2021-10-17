interface SplashCoreDependencies {
    fun exposeGlobalNavigator(): GlobalNavigator
}

internal class SplashCoreDependenciesDelegate(
    private val coreComponent: AppComponent
) : SplashCoreDependencies {

    override fun exposeGlobalNavigator(): GlobalNavigator {
        return coreComponent.exposeGlobalNavigator()
    }
}