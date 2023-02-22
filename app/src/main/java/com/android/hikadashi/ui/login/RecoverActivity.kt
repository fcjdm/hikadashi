package com.android.hikadashi.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.hikadashi.HostActivity
import com.android.hikadashi.databinding.ActivityAuthBinding
import com.android.hikadashi.databinding.ActivityRecoverBinding
import com.google.firebase.auth.FirebaseAuth

class RecoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecoverBinding.inflate(layoutInflater).apply {
            setContentView(root)

            recoverButton.setOnClickListener {
                if (emailEditText.text.isNotEmpty()) {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(
                        emailEditText.text.toString()
                    ).addOnCompleteListener() {
                        if (it.isSuccessful) {
                            successAlert()
                        } else {
                            errorAlert(it.exception!!.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun errorAlert(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun successAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Succesful!")
        builder.setMessage("Email para recuperar tu contraseña enviado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}