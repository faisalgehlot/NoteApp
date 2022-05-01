package com.faisalgehlot.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faisalgehlot.notes.databinding.NotesItemViewBinding

class NotesRecyclerViewAdapter(private val listener: ClickedNotesRVAdapter): RecyclerView.Adapter<NotesRecyclerViewAdapter.NotesRecyclerViewViewHolder>() {

    private val allNote = ArrayList<Note>()

    inner class NotesRecyclerViewViewHolder(binding: NotesItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        val textView = binding.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesRecyclerViewViewHolder {
        val view = NotesItemViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        val viewHolder = NotesRecyclerViewViewHolder(view)
        view.deleteButton.setOnClickListener {
            listener.onItemClicked(allNote[viewHolder.adapterPosition])
        }
        return NotesRecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesRecyclerViewAdapter.NotesRecyclerViewViewHolder, position: Int) {
        val currentNote = allNote[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNote.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newsList: List<Note>) {
        allNote.clear()
        allNote.addAll(newsList)

        notifyDataSetChanged()
    }
}
interface ClickedNotesRVAdapter {
    fun onItemClicked(note: Note)
}

