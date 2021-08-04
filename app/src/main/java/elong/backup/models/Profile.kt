package elong.backup.models

data class Profile (val login: String, val avatar_url:String)
/*trong API: https://api.github.com/users/educlong chỉ lấy 2 thuộc tính là login và avatar_url*/