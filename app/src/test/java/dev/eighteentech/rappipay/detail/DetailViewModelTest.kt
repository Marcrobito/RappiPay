package dev.eighteentech.rappipay.detail

import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Type
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals


class DetailViewModelTest {

    private val repository = DetailRepositoryMock()
    private val viewModel: DetailViewModel = DetailViewModel(repository)

    @Test
    fun testVideoSuccessfulCall() = runBlockingTest {
        repository.willSucceed = true
        assertEquals(viewModel.video.value, Response.NotStarted)
        val expectedResult = Response.Success(DetailRepositoryMock.video)
        viewModel.loadDetail("", Type.Movie)
        assertEquals(viewModel.video.value, Response.Loading)
        //assertEquals(viewModel.video.getOrAwaitValue(), expectedResult)
    }

}