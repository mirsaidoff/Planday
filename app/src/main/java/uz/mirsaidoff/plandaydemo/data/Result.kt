package uz.mirsaidoff.plandaydemo.data

import uz.mirsaidoff.plandaydemo.data.model.Paging

sealed class Result<out T : Any> {

    data class Success<out T : Any>(
        val paging: Paging,
        val data: List<T>
    ) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}