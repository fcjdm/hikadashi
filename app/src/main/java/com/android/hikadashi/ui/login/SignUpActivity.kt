package com.android.hikadashi.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.hikadashi.HostActivity
import com.android.hikadashi.databinding.ActivityAuthBinding
import com.android.hikadashi.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater).apply {
            setContentView(root)

            signupButton.setOnClickListener {
                if (emailEditText.text.isNotEmpty() && passEditText.text.isNotEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
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
        }
    }

    private fun login(email: String){
        val homeIntent: Intent = Intent(this, HostActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
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