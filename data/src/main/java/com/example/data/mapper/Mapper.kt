package com.example.data.mapper

interface Mapper<in From, out To> {
    fun map(from: From): To
}