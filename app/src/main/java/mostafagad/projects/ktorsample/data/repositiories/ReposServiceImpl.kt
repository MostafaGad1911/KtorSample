package mostafagad.projects.ktorsample.data.repositiories

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import mostafagad.projects.ktorsample.data.utils.Constants.ACCESS_TOKEN
import mostafagad.projects.ktorsample.data.utils.RemoteRoutes
import mostafagad.projects.ktorsample.domain.entities.ProfileDTO
import mostafagad.projects.ktorsample.domain.entities.RepoDTO
import mostafagad.projects.ktorsample.domain.entities.StarredRepoDTO
import mostafagad.projects.ktorsample.domain.repositiories.ReposServiceRepository
import javax.inject.Inject

class ReposServiceImpl @Inject constructor(private val client: HttpClient) :
    ReposServiceRepository {

    override suspend fun getRepositories(userName: String): List<RepoDTO> {
        return client.get(
            "${RemoteRoutes.BASE_URL}users/$userName/repos?type=private&sort=updated"
        ){
            header("Authorization", "token $ACCESS_TOKEN")
        }
    }

    override suspend fun getStarredRepositories(userName: String): List<StarredRepoDTO> {
        return client.get(
            "${RemoteRoutes.BASE_URL}users/$userName/starred"
        ){
            header("Authorization", "token $ACCESS_TOKEN")
        }
    }

    override suspend fun getProfile(userName: String): ProfileDTO {
        return client.get(
            "${RemoteRoutes.BASE_URL}users/$userName"
        ){
            header("Authorization", "token $ACCESS_TOKEN")
        }
    }
}