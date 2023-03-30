package org.d3if00001.catatanapp.notes.ui.insert

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import org.d3if00001.catatanapp.R
import org.d3if00001.catatanapp.databinding.ActivityNoteAddUpdateBinding
import org.d3if00001.catatanapp.notes.NoteAddUpdateViewModel
import org.d3if00001.catatanapp.notes.entity.Note
import org.d3if00001.catatanapp.notes.helper.DateHelper
import org.d3if00001.catatanapp.notes.viewModelFactory

class NoteAddUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddUpdateBinding
    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
    private var isEdit = false
    private var note: Note? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteAddUpdateViewModel = obtainViewModel(this@NoteAddUpdateActivity)

        if (VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_NOTE,Note::class.java)
        }else{
            note = intent.getParcelableExtra(EXTRA_NOTE)
        }
        if (note != null) {
            isEdit = true
        } else {
            note = Note()
        }

        binding.submitButton.setOnClickListener {
            val title = binding.tiTitle.text.toString().trim()
            val body = binding.tiIsi.text.toString().trim()
            val category = binding.tiKategori.text.toString().trim()
            when{
                title.isEmpty()-> binding.tiTitle.error = getString(R.string.empty)
                body.isEmpty()->binding.tiIsi.error = getString(R.string.empty)
                category.isEmpty()->binding.tiIsi.error = getString(R.string.empty)
                else->{
                    note.let {
                        it?.title=title
                        it?.body=body
                        it?.category=category
                    }
                    if(isEdit){
                        noteAddUpdateViewModel.update(note as Note)
                        showToast("Berhasil Ditambahkan!")
                    }else{
                        note.let { note ->
                            note?.date = DateHelper.getCurrentDate()
                        }
                        noteAddUpdateViewModel.insert(note as Note)
                        showToast("Berhasil Ditambahkan!")
                    }
                    finish()
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit) menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = viewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[NoteAddUpdateViewModel::class.java]
    }
    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    noteAddUpdateViewModel.delete(note as Note)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}