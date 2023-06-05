package mostafagad.projects.ktorsample.app.presentation.myRepos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StarredReposSection(myReposVM:MyReposVM) {
    val repos = myReposVM.listOfStarredRepos.observeAsState().value
    repos?.let {
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (repo in repos) {
                    item {
                        StarredRepoItem(repo = repo, onRepoClick = { url ->
                            myReposVM.updateWebUrl(url = url)
                        })
                    }
                }
            }
        }
    }
}