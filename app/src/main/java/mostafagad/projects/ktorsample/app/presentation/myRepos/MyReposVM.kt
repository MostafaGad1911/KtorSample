package mostafagad.projects.ktorsample.app.presentation.myRepos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.fold
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.features.ResponseException
import kotlinx.coroutines.launch
import mostafagad.projects.ktorsample.domain.entities.RepoDTO
import mostafagad.projects.ktorsample.domain.usecase.GetReposUseCase
import javax.inject.Inject

@HiltViewModel
class MyReposVM @Inject constructor(private val reposServiceUseCase: GetReposUseCase) :
    ViewModel() {
    private val _listOfRepos = MutableLiveData<List<RepoDTO>>()
    val listOfRepos: LiveData<List<RepoDTO>>
        get() = _listOfRepos

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage


    init {
        viewModelScope.launch {
            getRepos(userName = "MostafaGad1911")
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
}