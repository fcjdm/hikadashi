package com.android.hikadashi.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.android.hikadashi.HostActivity
import com.android.hikadashi.R
import com.android.hikadashi.databinding.ActivityAuthBinding
import com.android.hikadashi.databinding.FragmentDetailBinding
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthBinding.inflate(layoutInflater).apply {
            setContentView(root)

            loginButton.setOnClickListener {
                if (emailEditText.text.isNotEmpty() && passEditText.text.isNotEmpty()) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passEditText.text.toString()
                    ).addOnCompleteListener() {
                        if (it.isSuccessful) {
                            login(it.result.user!!.email!!)
                        } else {
                            errorAlert(it.exception!!.message.toString())
                        }
                    }
                }
            }

            signupButton.setOnClickListener{
                startActivity(Intent(this@AuthActivity, SignUpActivity::class.java))
            }
            recoverButton.setOnClickListener{
                startActivity(Intent(this@AuthActivity, RecoverActivity::class.java))
            }


        }
    }

    private fun login(email: String){
        startActivity(Intent(this, HostActivity::class.java))
    }

    private fun errorAlert(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}