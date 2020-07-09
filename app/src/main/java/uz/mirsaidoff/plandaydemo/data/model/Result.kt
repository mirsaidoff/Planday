package uz.mirsaidoff.plandaydemo.data.model

class Result<T>(
    val paging: Paging?,
    val data: T?,
    val error: ErrorResult?
) {
    fun isSuccess() = error == null
}