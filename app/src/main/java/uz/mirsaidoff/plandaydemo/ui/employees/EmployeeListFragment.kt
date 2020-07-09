package uz.mirsaidoff.plandaydemo.ui.employees

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_employee_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.mirsaidoff.plandaydemo.R

class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    companion object {
        fun newInstance() = EmployeeListFragment()
    }

    private val employeesViewModel: EmployeesViewModel by viewModel()
    private var clickListener: OnEmployeeClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEmployeeClickListener) clickListener = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = EmployeesAdapter(clickListener)
        rv_employees.adapter = adapter
        rv_employees.layoutManager = LinearLayoutManager(requireContext())
        employeesViewModel.fetchEmployees()
        employeesViewModel.resultLive.observe(viewLifecycleOwner) { adapter.setItems(it) }
    }
}