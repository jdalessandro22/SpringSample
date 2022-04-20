package org.example.utils

fun DoubleArray.sum() = this.reduce { total, next -> total + next }

fun DoubleArray.product() = this.reduce { total, next -> total * next }