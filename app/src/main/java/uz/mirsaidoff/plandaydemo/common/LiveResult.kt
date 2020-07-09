package uz.mirsaidoff.plandaydemo.common

sealed class LiveResult {
    class Success<T>(data: T) : LiveResult()
    class Fail(message: String) : LiveResult()
}