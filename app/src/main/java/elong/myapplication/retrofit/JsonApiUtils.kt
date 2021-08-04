package elong.myapplication.retrofit

import elong.myapplication.retrofit.RetrofitServices


class JsonApiUtils(val url:String) {
    public fun getDataJsonApi(): JsonApi {
        return RetrofitServices(url).Retrofits!!.create<JsonApi>(JsonApi::class.java)
    }
}