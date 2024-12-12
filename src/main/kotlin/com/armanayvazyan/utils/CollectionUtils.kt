import com.armanayvazyan.core.Endpoint
import com.google.gson.JsonArray
import com.google.gson.JsonElement

object CollectionUtils {

    private val listOfUrls: ArrayList<Endpoint> = ArrayList()

    fun getAllEndpointsFromGivenFile(jsonArray: JsonArray): ArrayList<Endpoint> {
        jsonArray.forEach {
            if (it.asJsonObject.has("request")) {
                val request = it.asJsonObject.get("request").asJsonObject
                if(request.has("method") && request.has("url")) {
                    val method = request.get("method").asString
                    val url = request.get("url").asJsonObject.get("raw").asString.substringBefore('?')
                    listOfUrls.add(Endpoint(method, url))
                }
            } else {
                getAllEndpointsFromGivenFile(it.iterateOverItem())
            }
        }
        return listOfUrls
    }
}

fun JsonElement.iterateOverItem(): JsonArray {
    return this.asJsonObject
        .get("item")
        .asJsonArray
}
