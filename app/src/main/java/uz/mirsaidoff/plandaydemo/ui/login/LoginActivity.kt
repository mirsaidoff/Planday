package uz.mirsaidoff.plandaydemo.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.mirsaidoff.plandaydemo.R
import uz.mirsaidoff.plandaydemo.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener { loginViewModel.login() }
        initObservers()
    }

    private fun initObservers() {
        loginViewModel.resultLive.observe(this) { success ->
            if (success) navigateToMainPage()
        }
        loginViewModel.errorLive.observe(this) {
            showLoginFailed(it)
        }
        loginViewModel.progressLive.observe(this) {
            loading.isVisible = it
            login.isEnabled = !it
        }
    }

    private fun navigateToMainPage() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun showLoginFailed(errorMessage: String) {
        AlertDialog.Builder(this)
            .setCancelable(true)
            .setMessage(errorMessage)
            .setNeutralButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}