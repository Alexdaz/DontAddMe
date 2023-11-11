package net.alex.dontaddme

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edPhone:TextInputEditText = findViewById(R.id.edPhone)
        val edMessa:TextInputEditText = findViewById(R.id.edMessage)
        val btnSend:Button            = findViewById(R.id.btnSend)

        btnSend.setOnClickListener {

            if(edPhone.text.isNullOrBlank())
            {
                Toast.makeText(this@MainActivity,
                    R.string.toastMSG, Toast.LENGTH_SHORT).show()
            }
            else
            {
                var intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse(buildURI(edPhone.text.toString(), edMessa.text.toString())))

                startActivity(intent)
            }
        }

    }

    private fun buildURI(phNUm:String, msg:String): String
    {
        var phURL = "https://wa.me/${phNUm}".filterNot { it.isWhitespace() }

        return if (msg.isNullOrBlank()) {
            phURL
        } else {
            var msgURL = msg.replace(" ", "%20")

            "$phURL?text=$msgURL"
        }
    }
}