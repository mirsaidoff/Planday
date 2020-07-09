package uz.mirsaidoff.plandaydemo.ui.employees

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*
import uz.mirsaidoff.plandaydemo.R
import uz.mirsaidoff.plandaydemo.data.model.Employee
import uz.mirsaidoff.plandaydemo.ui.employees.EmployeesAdapter.*

class EmployeesAdapter(private val listener: OnEmployeeClickListener?) :
    RecyclerView.Adapter<EmployeeViewHolder>() {

    private var _items: MutableList<Employee> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(_items[position], listener)
    }

    fun setItems(newItems: List<Employee>) {
        this._items.clear()
        this._items.addAll(newItems)
        this.notifyDataSetChanged()
    }

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Employee, listener: OnEmployeeClickListener?) = with(itemView) {
            tv_name.text = "${item.firstName} ${item.lastName}"
            tv_email.text = item.email
            itemView.setOnClickListener { listener?.onClick(item.id) }
        }
    }
}