package mostafagad.projects.ktorsample.domain.usecase

import mostafagad.projects.ktorsample.domain.repositiories.ReposServiceRepository
import javax.inject.Inject

class GetReposUseCase  @Inject constructor(private val reposServiceRepositories: ReposServiceRepository){
    suspend operator fun invoke(userName: String)= reposServiceRepositories.getRepositories(userName = userName)
}