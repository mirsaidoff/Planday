package uz.mirsaidoff.plandaydemo.data

import uz.mirsaidoff.plandaydemo.data.model.Employee
import uz.mirsaidoff.plandaydemo.network.ApiService

class EmployeeRepository(private val apiService: ApiService) {

    fun save(id: Long, body: Employee) = apiService.editEmployeeV1(id, body)

    fun loadById(id: Long) = apiService.getEmployeeByIdV1(id)

}