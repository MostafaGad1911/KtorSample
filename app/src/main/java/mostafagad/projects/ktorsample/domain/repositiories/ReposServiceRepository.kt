package mostafagad.projects.ktorsample.domain.repositiories

import arrow.core.Either
import mostafagad.projects.ktorsample.domain.entities.ProfileDTO
import mostafagad.projects.ktorsample.domain.entities.RepoDTO
import mostafagad.projects.ktorsample.domain.entities.StarredRepoDTO

interface ReposServiceRepository {
    suspend fun getRepositories(userName:String):List<RepoDTO>
    suspend fun getStarredRepositories(userName:String):List<StarredRepoDTO>

    suspend fun getProfile(userName:String):ProfileDTO

}