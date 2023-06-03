package mostafagad.projects.ktorsample.data.repositiories

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.parseQueryString
import mostafagad.projects.ktorsample.data.utils.RemoteRoutes
import mostafagad.projects.ktorsample.domain.entities.RepoDTO
import mostafagad.projects.ktorsample.domain.repositiories.ReposServiceRepository
import javax.inject.Inject

class ReposServiceImpl @Inject constructor(private val client: HttpClient) :
    ReposServiceRepository {

    override suspend fun getRepositories(userName: String): List<RepoDTO> {
        return client.get(
            "${RemoteRoutes.BASE_URL}users/$userName/repos?type=private&sort=updated"
        )


    }
}