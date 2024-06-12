package com.example.core.mapper

class IdentityMapper<T> : Mapper<T, T> {
    override fun map(input: T): T {
        return input
    }
}