package com.mp.tifosifutsal.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.mp.tifosifutsal.HomeActivity
import com.mp.tifosifutsal.Preferences
import com.mp.tifosifutsal.R
import com.mp.tifosifutsal.sign.signin.SignInActivity
import com.mp.tifosifutsal.sign.signin.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var iNama : String
    lateinit var iAlamat : String
    lateinit var iNohp : String
    lateinit var iUsername : String
    lateinit var iPassword : String

    private lateinit var mFirebaseDatabase : DatabaseReference
    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var mDatabase : DatabaseReference

    private lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("user")

        preferences = Preferences(this)

        btn_registrasi.setOnClickListener {
            iNama = et_nama.text.toString()
            iAlamat = et_alamat.text.toString()
            iNohp = et_hp.text.toString()
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iNama.equals("")) {
                et_nama.error = "Silahkan Isi Nama Anda"
                et_nama.requestFocus()
            } else if (iAlamat.equals("")) {
                et_alamat.error = "Silahkan Isi Alamat Anda"
                et_alamat.requestFocus()
            } else if (iNohp.equals("")) {
                et_hp.error = "Silahkan Isi No HP Anda"
                et_hp.requestFocus()
            } else if (iUsername.equals("")) {
                et_username.error = "Silahkan Isi Username Anda"
                et_username.requestFocus()
            } else if (iPassword.equals("")) {
                et_password.error = "Silahkan Isi Password Anda"
                et_password.requestFocus()
            } else {
                saveUser(iNama, iAlamat, iNohp, iUsername, iPassword)
            }
        }

        btn_login.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
    private fun saveUser(sNama : String, sAlamat :String, sNohp : String, sUsername : String, sPassword: String){
        val user = User()
        user.nama = sNama
        user.alamat = sAlamat
        user.no_hp = sNohp
        user.username = sUsername
        user.password = sPassword

        if (sUsername != null){
            chekingUsername(sUsername, user)
        }
    }
    private fun chekingUsername(iUsername: String, data: User) {
        mFirebaseDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mFirebaseDatabase.child(iUsername).setValue(data)

                    preferences.setValues("nama", data.toString())
                    preferences.setValues("alamat", data.toString())
                    preferences.setValues("no_hp", data.toString())
                    preferences.setValues("username", data.toString())
                    preferences.setValues("password", data.toString())
                    preferences.setValues("status", "1")


                    val intent = Intent(this@SignUpActivity, SignUpPhotoscreen::class.java).putExtra("nama",data.nama)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUpActivity, "User Telah Digunakan", Toast.LENGTH_LONG)
                        .show()

                }
            }

            override fun onCancelled(eror: DatabaseError) {

                Toast.makeText(this@SignUpActivity, "Registrasi gagal"+eror, Toast.LENGTH_LONG).show()
            }
        })
    }
}
