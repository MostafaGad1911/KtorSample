package mostafagad.projects.ktorsample.domain.entities

import kotlinx.serialization.Serializable
import mostafagad.projects.ktorsample.data.models.Profile

@Serializable
data class ProfileDTO(
    val avatar_url: String? = null,
    val bio: String? = null,
    val blog: String? = null,
    val company: String? = null,
    val created_at: String? = null,
    val email: String? = null,
    val events_url: String? = null,
    val followers: Int? = null,
    val followers_url: String? = null,
    val following: Int? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val gravatar_id: String? = null,
    val html_url: String? = null,
    val id: Int? = null,
    val location: String? = null,
    val login: String? = null,
    val name: String? = null,
    val node_id: String? = null,
    val organizations_url: String? = null,
    val public_gists: Int? = null,
    val public_repos: Int? = null,
    val received_events_url: String? = null,
    val repos_url: String? = null,
    val site_admin: Boolean? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val twitter_username: String? = null,
    val type: String? = null,
    val updated_at: String? = null,
    val url: String? = null
){
    fun toEntity(): Profile = Profile(name = name , img = avatar_url, bio = bio , followers = followers , following = following , emails = email , public_repos = public_repos , location = location , work = company)

}