package com.example.strarterandroid


import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.core.model.mock.MockDataGithubRepos
import com.example.strarterandroid.network.mock.FakeApiCall
import com.example.strarterandroid.network.mock.FakeGithubRepository
import com.example.strarterandroid.presentation.main_screen.MainViewModel
import com.example.strarterandroid.presentation.shared.mock_checker.FakeNetworkChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // System under test
    private lateinit var viewModel: MainViewModel

    // Test data
    private val fakeApiCall = FakeApiCall() // Assuming you have a similar Fake class for IApiCall
    private val fakeGithubRepository = FakeGithubRepository()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val fakeNetworkChecker = FakeNetworkChecker(networkAvailable = true) // Simulate network availability
        viewModel = MainViewModel(fakeApiCall, fakeGithubRepository, fakeNetworkChecker)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when API fetch is successful, viewState should be Success`() = runBlockingTest {
        // Given
        val expectedData = MockDataGithubRepos.mockGithubReposListModelList
        fakeApiCall.reposListApiResponse = Response.success(expectedData)

        // When
        viewModel.callApi()
        val value = viewModel.viewState.first()

        // Then
        assertTrue(value is MainViewState.Success && value.data == expectedData)
    }

    @Test
    fun `when API fetch returns null body, viewState should be Error`() = runBlockingTest {
        // Given
        fakeApiCall.reposListApiResponse = Response.success(null)

        // When
        viewModel.callApi()
        val value = viewModel.viewState.first()

        // Then
        assertTrue(value is MainViewState.Error && value.error == "Response body is null")
    }

    @Test
    fun `when API fetch fails, viewState should be Error`() = runBlockingTest {
        // Given
        fakeApiCall.exception = Exception("Network error")

        // When
        viewModel.callApi()
        val value = viewModel.viewState.first()

        // Then
        assertTrue(value is MainViewState.Error && value.error.contains("Network error"))
    }

    @Test
    fun `when DB has data, viewState should be Success`() = runBlockingTest {
        // Given
        val expectedData = MockDataGithubRepos.mockGithubReposListModelList
        fakeGithubRepository.reposList = expectedData

        // When
        viewModel.fetchReposFromDatabase()
        val value = viewModel.viewState.first()

        // Then
        assertTrue(value is MainViewState.Success && value.data == expectedData)
    }

    @Test
    fun `when DB is empty, viewState should be Error`() = runBlockingTest {
        // Given
        fakeGithubRepository.shouldReturnEmptyList = true

        // When
        viewModel.fetchReposFromDatabase()
        val value = viewModel.viewState.first()

        // Then
        assertTrue(value is MainViewState.Error && value.error == "No data available")
    }

    @Test
    fun `retry should re-initiate API fetch`() = runBlockingTest {
        // Prepare fakeApiCall to respond to retries
        // ...

        // When
        viewModel.retry()
        // Advance coroutine time or wait if necessary

        // Then
        // Verify that API call was attempted again
    }

    // Additional tests...
}


