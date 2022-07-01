package com.matsuyami.recipy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matsuyami.recipy.R
import com.recipy.models.Instruction

class InstructionsAdapter(private val instructions: List<Instruction>) : RecyclerView.Adapter<InstructionsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val instructionText: TextView = itemView.findViewById(R.id.instructionText)
        val instructionPos: TextView = itemView.findViewById(R.id.instructionPos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // creates a new view which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.instruction_row_item, parent, false)
        return ViewHolder(view)
    }

    // replace the contents of the view invoked by the layout manager
    override fun onBindViewHolder(holder: InstructionsAdapter.ViewHolder, position: Int) {
        // get elem from instructions and replace tv text with that elem text
        val realPosition = position + 1
        holder.instructionText.text = instructions[position].displayText
        holder.instructionPos.text = realPosition.toString()
    }

    override fun getItemCount() = instructions.size
}
