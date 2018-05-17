package com.knoldus.utils

case class SuccessResponse(message: String)

case class ErrorResponse(error: String, status: Int)