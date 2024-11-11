package ro.pub.cs.systems.eim.colocviu1_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Colocviu1_1SecondActivity : AppCompatActivity() {
    private lateinit var edit_text: EditText
    private lateinit var register_btn: Button
    private lateinit var cancel_btn: Button

    private var no_btn_presses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colocviu1_1_second)

        edit_text = findViewById<EditText>(R.id.editText2)
        register_btn = findViewById<Button>(R.id.register_btn)
        cancel_btn = findViewById<Button>(R.id.cancel_btn)

        edit_text.setText(intent.getStringExtra(Constants.EDIT_TEXT))
        no_btn_presses = intent.getIntExtra(Constants.NO_BTN_PRESSES.toString(), Constants.NO_BTN_PRESSES)

        register_btn.setOnClickListener { view ->
            val result = Intent()
            setResult(Constants.REGISTER_BTN, result)
            finish()
        }

        cancel_btn.setOnClickListener { view ->
            val result = Intent()
            setResult(Constants.CANCEL_BTN, result)
            finish()
        }
    }
}