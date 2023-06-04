package mostafagad.projects.ktorsample.app.presentation.myRepos

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ForkedReposSection(myReposVM: MyReposVM) {
    val context = LocalContext.current
    val repos = myReposVM.listOfRepos.observeAsState().value?.filter { it.fork == true}
    repos?.let {
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (repo in repos) {
                    item {
                        RepoItem(repo = repo, onRepoClick = { url ->
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(url)
                            context.startActivity(i)
                        })
                    }
                }
            }
        }
    }
}