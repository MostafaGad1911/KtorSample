package mostafagad.projects.ktorsample.domain.entities

import kotlinx.serialization.Serializable
import mostafagad.projects.ktorsample.data.models.Owner

@Serializable
data class OwnerDTO(
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
) {

    fun toEntity():Owner = Owner(id = id , avatar = avatar_url , url = html_url , name = login)
}