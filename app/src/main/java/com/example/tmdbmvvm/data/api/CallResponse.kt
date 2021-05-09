package com.example.tmdbmvvm.data.api

class CallResponse() {

    suspend fun responseCallMovie(movieId: Int): GetResponseApi {
        return try {
            val response = apiServiceReturnMovie(movieId)

            if (response.isSuccessful) {
                GetResponseApi.ResponseSucess(response.body())
            } else {
                if (response.code() == 404) {
                    GetResponseApi.ResponseError("Dado não Encontrado")
                } else {
                    GetResponseApi.ResponseError("Erro ao carregar os Dados")
                }
            }
        } catch (exception: Exception) {
            GetResponseApi.ResponseError("Erro ao carergar os dados")
        }
    }

    suspend fun responseCallSimilar(movieId: Int): GetResponseApi {
        return try {
            val response = apiServiceReturnSimilar(movieId)

            if (response.isSuccessful) {
                GetResponseApi.ResponseSucess(response.body())
            } else {
                if (response.code() == 404) {
                    GetResponseApi.ResponseError("Dado não Encontrado")
                } else {
                    GetResponseApi.ResponseError("Erro ao carregar os Dados")
                }
            }

        } catch (exception: Exception) {
            GetResponseApi.ResponseError("Erro ao carergar os dados")
        }

    }

    suspend fun responseCallGenre(genreId: Int): GetResponseApi {
        return try {
            val response = apiServiceReturnGenre(genreId)

            if (response.isSuccessful) {
                GetResponseApi.ResponseSucess(response.body())
            } else {
                if (response.code() == 404) {
                    GetResponseApi.ResponseError("Dado não Encontrado")
                } else {
                    GetResponseApi.ResponseError("Erro ao carregar os Dados")
                }
            }

        } catch (exception: Exception) {
            GetResponseApi.ResponseError("Erro ao carergar os dados")
        }

    }

    private suspend fun apiServiceReturnMovie(movieId: Int) = ApiService.tmdbApi.getMovie(movieId)
    private suspend fun apiServiceReturnSimilar(movieId: Int) = ApiService.tmdbApi.getSimilarMovies(movieId)
    private suspend fun apiServiceReturnGenre(genreId: Int) = ApiService.tmdbApi.getGenres(genreId)
}
