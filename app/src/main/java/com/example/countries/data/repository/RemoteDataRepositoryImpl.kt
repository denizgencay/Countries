package com.example.countries.data.repository

import com.example.countries.data.remote.RapidApi
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.CountryDetail
import com.example.countries.domain.model.CountryResponse
import com.example.countries.domain.repository.RemoteDataRepository
import com.example.countries.util.Constants.API_KEY
import com.example.countries.util.Constants.DATA_PER_TIME
import com.example.countries.util.Resource
import com.example.countries.util.dispatcher_provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val rapidApi: RapidApi,
    private val dispatcherProvider: DispatcherProvider
): RemoteDataRepository {
    override suspend fun getAllCountries(): Flow<Resource<CountryResponse>> =
        callbackFlow {
            trySend(Resource.Loading())
            try {
                val response = rapidApi.getAllCountries(API_KEY, DATA_PER_TIME)
                trySend(Resource.Success(response))
                close()
            }catch (e: Exception){
                close()
            }
        }.flowOn(dispatcherProvider.mainImmediate)


    override suspend fun getSelectedCountry(countryCode: String): Flow<Resource<CountryDetail>> =
        callbackFlow {
            trySend(Resource.Loading())
            try {
                val response = rapidApi.getSelectedCountry(API_KEY, countryCode)
                trySend(Resource.Success(response))
                close()
            }catch (e: Exception){
                close()
            }
        }.flowOn(dispatcherProvider.mainImmediate)
}























