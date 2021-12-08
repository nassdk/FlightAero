package com.nassdk.featureflights.presentation.mvi

import com.nassdk.featureflights.base.BaseTest
import com.nassdk.featureflights.base.runBlocking
import com.nassdk.featureflights.domain.entity.FlightEntity
import com.nassdk.featureflights.domain.entity.FlightStatus
import com.nassdk.featureflights.domain.entity.PaginationEntity
import com.nassdk.featureflights.domain.entity.RTFlightsEntity
import com.nassdk.featureflights.domain.repo.FlightsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.then

@OptIn(ExperimentalCoroutinesApi::class)
internal class FlightsViewModelTest : BaseTest() {

    private val repository: FlightsRepository = mock()

    @Test
    fun `when vm created - then fetch flights`() = mainCoroutineRule.runBlocking {

        //When
        createViewModel()

        //Then
        then(repository).should().getRealTimeFlights(offset = 0)
    }

    @Test
    fun `when fetch flights - then success`() = mainCoroutineRule.runBlocking {

        //Given
        val mockedFlights = listOf(
            FlightEntity(
                status = FlightStatus.Canceled,
                number = "1234",
                arrivalTimezone = "Russia",
                arrivalTime = "13:15",
                departureTimezone = "Russia",
                departureTime = "11:30"
            )
        )

        given { runBlocking { repository.getRealTimeFlights(offset = 0) } }.willReturn(
            RTFlightsEntity(
                flights = mockedFlights,
                pagination = PaginationEntity(limit = 0, offset = 0, total = 0)
            )
        )

        //When
        val vm = createViewModel()

        //Then
        val actualState = vm.viewState().first()
        val state = FlightsViewState(
            isLoading = false,
            flights = mockedFlights,
            error = null,
            offset = 1,
            isLastPage = false
        )

        assert(state == actualState)
    }

    @Test
    fun `when fetch flights - then error`() = mainCoroutineRule.runBlocking {

        //Given
        val httpException = retrofit2.HttpException(
            retrofit2.Response.error<Unit>(404, "".toResponseBody())
        )
        given { runBlocking { repository.getRealTimeFlights(offset = 0) } }.willThrow(
            httpException
        )

        //When
        val vm = createViewModel()

        //Then
        val actualState = vm.viewState().first()
        val state = FlightsViewState(
            isLoading = true,
            flights = emptyList(),
            error = null,
            offset = 0,
            isLastPage = false
        )

        assert(state == actualState)
    }

    @Test
    fun `when fetch next page event - then fetch next page`() = runBlockingTest {

        //Given
        val flightsPage = retrieveFullPageFlights()
        given { runBlocking { repository.getRealTimeFlights(offset = 0) } }.willReturn(
            RTFlightsEntity(
                flights = flightsPage,
                pagination = PaginationEntity(limit = 0, offset = 0, total = 0)
            )
        )

        //When
        val vm = createViewModel()
        vm.perform(viewEvent = FlightsViewEvent.LoadMore)

        //Then
        then(repository).should().getRealTimeFlights(offset = 20)

        val actualState = vm.viewState().first()
        val state = FlightsViewState(
            isLoading = true,
            flights = flightsPage,
            error = null,
            offset = flightsPage.size,
            isLastPage = false
        )

        assert(actualState == state)
    }

    private fun retrieveFullPageFlights() = listOf(
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
        FlightEntity(status = FlightStatus.Canceled,
            number = "1234",
            arrivalTimezone = "Russia",
            arrivalTime = "13:15",
            departureTimezone = "Russia",
            departureTime = "11:30"),
    )

    private fun createViewModel(): FlightsViewModel {
        return FlightsViewModel(
            repository = repository,
            dispatcherProvider = dispatcherProvider
        )
    }
}