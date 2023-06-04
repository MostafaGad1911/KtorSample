package mostafagad.projects.ktorsample.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class RepoDTO(
    val id: Long,
    val owner:OwnerDTO,
    val name: String? = null,
    val language: String? = null,
    val forks_count:Int? = 0,
    val clone_url:String? = null ,
    val watchers: Int? = null,
    val fork:Boolean? = null
)
