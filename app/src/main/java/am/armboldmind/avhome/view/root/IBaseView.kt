package am.armboldmind.avhome.view.root

interface IBaseView {
    fun onToast(message: String?)

    fun onSnackBar(message: String?)

    fun showServerError()

    fun showNetworkError()
}