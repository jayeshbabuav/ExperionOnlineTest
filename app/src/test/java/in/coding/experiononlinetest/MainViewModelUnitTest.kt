package `in`.coding.experiononlinetest

import `in`.coding.experiononlinetest.configurations.RestCallInterface
import `in`.coding.experiononlinetest.models.MainResponseModel
import `in`.coding.experiononlinetest.viewmodels.MainViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call

@RunWith(MockitoJUnitRunner::class)
class MainViewModelUnitTest {

    @Mock
    lateinit var restCallInterface: RestCallInterface

    @Mock
    lateinit var data: Call<MainResponseModel>

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        // initialize the ViewModel
        mainViewModel = MainViewModel()
    }

    @Test
    fun getList_Test() {
        Mockito.`when`(restCallInterface.getList())
            .thenReturn(data)

        mainViewModel.getList()
    }

}