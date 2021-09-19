fun setNavigationHandler(navigationHandler: NavigationHandler)
    fun currentNavigationHandler(): NavigationHandler?

    fun showProgress(show: Boolean)
    fun onOpenActivity(intent: Intent)

    /**
     * Open bottom dialog
     * @param fragment - fragment with view for dialog
     * @param bottomDialogParams -  params for dialog
     */
    fun onOpenBottomFragment(
        fragment: Fragment,
        bottomDialogParams: BottomDialogParams = BottomDialogParams.DEFAULT
    )

    fun onCloseBottomFragment()
    fun openFragment(fragment: Fragment, addToBackStack: Boolean)
    fun openFragment(fragment: Fragment, addToBackStack: Boolean, overlay: Boolean = false)
    fun showMessage(messageBundle: MessageBundle)
    fun showWebPage(url: String)
    fun back()
    fun exit()

    /**
     * Открывает новый корневой фрагмент взамен всех других
     */
    fun newRootFragment(root: Fragment)

    fun requestPermissions(permissions: List<String>): Single<PermissionRequestResult>
    fun requestPermissions(vararg permissions: String): Single<PermissionRequestResult>

    fun showDialog(dialogId: UUID = UUID.randomUUID(), buildFunction: DialogBuilder.() -> Unit): Dialog
    fun showDialogFragment(
        dialogId: UUID = UUID.randomUUID(),
        fragment: DialogFragment,
        fullscreen: Boolean = false
    )

    fun hideDialog(dialogId: UUID)