package com.example.testapp.userinfo.objects


data class ResponseResult(val results: List<ResultsItem>, val info: Info) {
	val size: Int get() = results.size
	operator fun get(position: Int): ResultsItem = results[position]
}
