package mostafagad.projects.ktorsample.data.models

data class Profile(
    val name: String? = null,
    val img: String? = null,
    val bio: String? = null,
    val emails: String? = null,
    val following: Int? = null,
    val followers: Int? = null,
    val public_repos: Int? = null,
)
