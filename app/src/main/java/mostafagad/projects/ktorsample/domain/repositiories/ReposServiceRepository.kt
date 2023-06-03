package mostafagad.projects.ktorsample.domain.repositiories

import arrow.core.Either
import mostafagad.projects.ktorsample.domain.entities.RepoDTO

interface ReposServiceRepository {
    suspend fun getRepositories(userName:String):List<RepoDTO>
}