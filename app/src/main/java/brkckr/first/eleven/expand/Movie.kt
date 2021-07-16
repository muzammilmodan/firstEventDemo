package brkckr.first.eleven.expand

import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("name")
    private var name: String? = null

    @SerializedName("desc")
    private var desc: String? = null

    @SerializedName("imageUrl")
    private var imageUrl: String? = null

    @SerializedName("category")
    private var categoty: String? = null

    fun Movie(name: String?, desc: String?, imageUrl: String?, categoty: String?) {
        this.name = name
        this.desc = desc
        this.imageUrl = imageUrl
        this.categoty = categoty
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDesc(): String? {
        return desc
    }

    fun setDesc(desc: String?) {
        this.desc = desc
    }

    fun getImageUrl(): String? {
        return imageUrl
    }

    fun setImageUrl(imageUrl: String?) {
        this.imageUrl = imageUrl
    }

    fun getCategoty(): String? {
        return categoty
    }

    fun setCategoty(categoty: String?) {
        this.categoty = categoty
    }

    override fun toString(): String {
        return "Movie{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoty='" + categoty + '\'' +
                '}'
    }
}