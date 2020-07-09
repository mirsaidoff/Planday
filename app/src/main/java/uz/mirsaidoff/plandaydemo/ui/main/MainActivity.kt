package uz.mirsaidoff.plandaydemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import uz.mirsaidoff.plandaydemo.R
import uz.mirsaidoff.plandaydemo.data.model.Employee
import uz.mirsaidoff.plandaydemo.ui.employees.edit.EmployeeEditFragment
import uz.mirsaidoff.plandaydemo.ui.employees.EmployeeListFragment
import uz.mirsaidoff.plandaydemo.ui.employees.OnEmployeeClickListener

class MainActivity : AppCompatActivity(), OnEmployeeClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(container.id, EmployeeListFragment.newInstance())
                .commit()
        }
    }

    private fun navigateToDetailsPage(employeeId: Long) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EmployeeEditFragment.newInstance(employeeId))
            .addToBackStack(EmployeeEditFragment::class.java.name)
            .commit()
    }

    override fun onClick(employeeId: Long) {
        navigateToDetailsPage(employeeId)
    }
}