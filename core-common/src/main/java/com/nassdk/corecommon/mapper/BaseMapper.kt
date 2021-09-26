package com.nassdk.corecommon.mapper

interface BaseMapper<FROM, TO> {
    fun map(from: FROM): TO
}
