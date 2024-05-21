import com.example.core.Response
import com.example.domain.model.User
import com.example.domain.usecase.user.GetUserUseCase
import com.example.userfeature.presenter.user.UserViewModel
import com.example.userfeature.presenter.user.effect.UserEffect
import com.example.userfeature.presenter.user.intent.UserIntent
import com.example.userfeature.presenter.user.state.UserUIState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File
import java.lang.reflect.Type

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class UserViewModelTest {

    @MockK
    private lateinit var getUsersUseCase: GetUserUseCase
    private lateinit var userViewModel: UserViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        userViewModel = UserViewModel(getUsersUseCase)
    }

    @Test
    fun `Given user list, When loading users, Then loading state is emitted`() = runTest {
        coEvery { getUsersUseCase() } returns Response.Loading(true)

        userViewModel.processIntent(UserIntent.LoadUsers)
        val userState = userViewModel.userState.value
        assert(userState is UserUIState.Loading)
    }

    @Test
    fun `Given user list, When loading users, Then success state is emitted`() = runTest {
        val users = readUsersFromFile()
        coEvery { getUsersUseCase() } returns Response.Success(users)

        userViewModel.processIntent(UserIntent.LoadUsers)
        val userState = userViewModel.userState.value
        assert(userState is UserUIState.ShowContent)
        assert((userViewModel.userState.value as UserUIState.ShowContent).users == users)
    }

    @Test
    fun `Given user list, When loading users, Then error state is emitted`() = runTest {
        coEvery { getUsersUseCase() } returns Response.Error(Exception(ERROR_MESSAGE))

        userViewModel.processIntent(UserIntent.LoadUsers)

        val userState = userViewModel.userState.value
        assert(userState is UserUIState.Error)
    }

    @Test
    fun `Given user list with userid, When item clicked, Then emit side effect`() = runTest {
        userViewModel.processIntent(UserIntent.UIIntent.ListItemClicked(USER_ID))
        val job = launch {
            val userState = userViewModel.effect.first()
            assert(userState is UserEffect.NavigationEffect.NavigateUserInfoScreen)
        }
        job.cancel()
    }

    private fun readUsersFromFile(): List<User> {
        val jsonFile = File(javaClass.classLoader?.getResource(FILE_NAME)!!.file)
        val jsonString = jsonFile.readText()
        val listType: Type = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    private companion object {
        private const val ERROR_MESSAGE = "Error message"
        private const val FILE_NAME = "UserTestData.json"
        private const val USER_ID = "1"
    }
}