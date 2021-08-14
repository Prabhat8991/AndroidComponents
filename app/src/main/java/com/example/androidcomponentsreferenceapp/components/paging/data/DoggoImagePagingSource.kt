package com.example.androidcomponentsreferenceapp.components.paging.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.androidcomponentsreferenceapp.components.paging.model.DoggoImageModel
import com.example.androidcomponentsreferenceapp.components.paging.network.RemoteInjector
import retrofit2.HttpException

@ExperimentalPagingApi
class DoggoImagePagingSource(val doggoApiService: RemoteInjector.DoggoApiService): PagingSource<Int, DoggoImageModel>() {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DoggoImageModel> {
        val page = params.key?: DEFAULT_PAGE_INDEX
        return try {
            val response = doggoApiService.getDoggoImages(page, params.loadSize)
            LoadResult.Page(
                response, prevKey = if(page == DEFAULT_PAGE_INDEX) null else page-1,
                nextKey = if(response.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}