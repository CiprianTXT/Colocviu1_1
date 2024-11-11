package ro.pub.cs.systems.eim.colocviu1_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class Colocviu1_1MainActivity : AppCompatActivity() {
    private lateinit var north_btn: Button
    private lateinit var west_btn: Button
    private lateinit var east_btn: Button
    private lateinit var south_btn: Button
    private lateinit var switch_activity: Button

    private lateinit var activity_result_launcher: ActivityResultLauncher<Intent>

    private lateinit var edit_text: EditText
    private var no_btn_presses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colocviu1_1_main)

        north_btn = findViewById<Button>(R.id.north_btn)
        west_btn = findViewById<Button>(R.id.west_btn)
        east_btn = findViewById<Button>(R.id.east_btn)
        south_btn = findViewById<Button>(R.id.south_btn)
        switch_activity = findViewById<Button>(R.id.switch_activity)

        activity_result_launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Constants.REGISTER_BTN) {
                Toast.makeText(this, "Pressed Register", Toast.LENGTH_LONG).show()
            } else if (result.resultCode == Constants.CANCEL_BTN) {
                Toast.makeText(this, " Pressed Cancel", Toast.LENGTH_LONG).show()
            }
        }

        edit_text = findViewById<EditText>(R.id.editText1)

        edit_text.setText("")

        north_btn.setOnClickListener { view ->
            if (edit_text.text.isEmpty()) {
                edit_text.setText("North")
            } else {
                edit_text.setText(edit_text.text.toString() + ", North")
            }

            no_btn_presses++
        }
        west_btn.setOnClickListener { view ->
            if (edit_text.text.isEmpty()) {
                edit_text.setText("West")
            } else {
                edit_text.setText(edit_text.text.toString() + ", West")
            }

            no_btn_presses++
        }
        east_btn.setOnClickListener { view ->
            if (edit_text.text.isEmpty()) {
                edit_text.setText("East")
            } else {
                edit_text.setText(edit_text.text.toString() + ", East")
            }

            no_btn_presses++
        }
        south_btn.setOnClickListener { view ->
            if (edit_text.text.isEmpty()) {
                edit_text.setText("South")
            } else {
                edit_text.setText(edit_text.text.toString() + ", South")
            }

            no_btn_presses++
        }

        edit_text.setText(Constants.EDIT_TEXT)

        switch_activity.setOnClickListener { view ->
            val intent = Intent(this, Colocviu1_1SecondActivity::class.java)
            intent.putExtra(Constants.EDIT_TEXT, edit_text.text.toString())
            intent.putExtra(Constants.NO_BTN_PRESSES.toString(), no_btn_presses)
            
            edit_text.setText("")
            no_btn_presses = 0

            activity_result_launcher.launch(intent)
        }
    }

    @Override
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Constants.EDIT_TEXT, edit_text.text.toString())
        outState.putInt(Constants.NO_BTN_PRESSES.toString(), no_btn_presses)
    }

    @Override
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(Constants.EDIT_TEXT)) {
            edit_text.setText(savedInstanceState.getString(Constants.EDIT_TEXT))
        }
        if (savedInstanceState.containsKey(Constants.NO_BTN_PRESSES.toString())) {
            no_btn_presses = savedInstanceState.getInt(Constants.NO_BTN_PRESSES.toString())
        }
    }
}