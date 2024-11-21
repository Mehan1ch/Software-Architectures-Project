package hu.bme.aut.android.writer_reader_client.data.repository

import androidx.compose.foundation.pager.HorizontalPager
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import kotlinx.coroutines.flow.Flow
/*
class WorkDataSource (
    private val api: WriterReaderApi
){
    fun getAllWorks(): Flow<PagingData<Work>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { api.getWorks(1, 10) }
        ).flow
    }
}


class WorkPagingSource(
    private val api: WriterReaderApi
) : PagingSource<Int, Work>() {

    overr
}

*/