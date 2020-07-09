package uz.mirsaidoff.plandaydemo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee(
    val id: Long,
    val dateTimeCreated: String,        // 2018-06-14
    val dateTimeModified: String?,       // 2018-06-15T13:45:53.327Z
    val bankAccount: String?,
    val employeeTypeId: Long?,
    val salaryIdentifier: String?,
    val hireDate: String?,
    val deactivationDate: String?,
    val firstName: String?,
    val lastName: String?,
    val userName: String?,
    val cellPhone: String?,
    val birthDate: String?,
    val ssn: String?,
    val cellPhoneCountryCode: String?,
    val street1: String?,
    val zip: String?,
    val phone: String?,
    val phoneCountryCode: String?,
    val city: String,
    val email: String,
    val departments: List<Long>,
    val employeeGroups: List<Long>
) : Parcelable