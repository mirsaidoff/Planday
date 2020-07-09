package uz.mirsaidoff.plandaydemo.ui.employees.edit

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.mirsaidoff.plandaydemo.R
import uz.mirsaidoff.plandaydemo.common.EMPLOYEE_KEY
import uz.mirsaidoff.plandaydemo.common.LiveResult

class EmployeeEditFragment : Fragment(R.layout.fragment_employee_edit) {
    companion object {
        fun newInstance(employeeId: Long) = EmployeeEditFragment()
            .apply {
                arguments = bundleOf(EMPLOYEE_KEY to employeeId)
            }
    }

    private val employeeViewModel: EmployeeEditViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val employeeId = arguments?.getLong(EMPLOYEE_KEY) ?: 0

        employeeViewModel.loadEmployeeById(employeeId)
        initObservers()
    }

    private fun initObservers() {
        employeeViewModel.resultLive.observe(viewLifecycleOwner) {
            if (it is LiveResult.Success<*>) {
                //todo fill
            } else if (it is LiveResult.Fail) {
                //todo show error
            }
        }
        employeeViewModel.progressLive.observe(viewLifecycleOwner) {
            //todo show/hide progress
        }
    }
}