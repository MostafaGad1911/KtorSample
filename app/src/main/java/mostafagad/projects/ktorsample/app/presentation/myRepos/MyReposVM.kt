package mostafagad.projects.ktorsample.app.presentation.myRepos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.features.ResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mostafagad.projects.ktorsample.domain.entities.ProfileDTO
import mostafagad.projects.ktorsample.domain.entities.RepoDTO
import mostafagad.projects.ktorsample.domain.entities.StarredRepoDTO
import mostafagad.projects.ktorsample.domain.usecase.GetProfileUseCase
import mostafagad.projects.ktorsample.domain.usecase.GetReposUseCase
import mostafagad.projects.ktorsample.domain.usecase.GetStarredReposUseCase
import javax.inject.Inject

@HiltViewModel
class MyReposVM @Inject constructor(
    private val reposServiceUseCase: GetReposUseCase,
    private val getStarredReposUseCase: GetStarredReposUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) :
    ViewModel() {
    private val _listOfRepos = MutableLiveData<List<RepoDTO>>()
    val listOfRepos: LiveData<List<RepoDTO>>
        get() = _listOfRepos

    private val _listOfStarredRepos = MutableLiveData<List<StarredRepoDTO>>()
    val listOfStarredRepos: LiveData<List<StarredRepoDTO>>
        get() = _listOfStarredRepos

    private val _myProfile = MutableLiveData<ProfileDTO>()
    val myProfile: LiveData<ProfileDTO>
        get() = _myProfile


    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage


    init {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                getRepos(userName = "MostafaGad1911")
            }
            withContext(Dispatchers.Default) {
                getStarredRepos(userName = "MostafaGad1911")
            }
            withContext(Dispatchers.Default) {
                getProfile(userName = "MostafaGad1911")
            }
        }
    }

    private suspend fun getRepos(userName: String) {
        try {
            val result = reposServiceUseCase.invoke(userName = userName)
            _listOfRepos.postValue(result.sortedByDescending { it.watchers })
        } catch (e: ResponseException) {
            _errorMessage.postValue(e.response.status.description)
        }
    }

    private suspend fun getStarredRepos(userName: String) {
        try {
            val result = getStarredReposUseCase.invoke(userName = userName)
            _listOfStarredRepos.postValue(result)
        } catch (e: ResponseException) {
            _errorMessage.postValue(e.response.status.description)
        }
    }

    private suspend fun getProfile(userName: String) {
        try {
            val result = getProfileUseCase.invoke(userName = userName)
            _myProfile.postValue(result)
        } catch (e: ResponseException) {
            _errorMessage.postValue(e.response.status.description)
        }
    }
}