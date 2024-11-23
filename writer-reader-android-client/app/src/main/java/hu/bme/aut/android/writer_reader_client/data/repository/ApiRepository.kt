package hu.bme.aut.android.writer_reader_client.data.repository

import hu.bme.aut.android.writer_reader_client.data.model.post.CommentRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeResponse
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import retrofit2.Callback

class ApiRepository(private val api: WriterReaderApi) {

    fun postComment(
        token: String,
        commentRequest: CommentRequest,
        callback: Callback<CommentResponse>
    ) {
        api.postComment("Bearer $token", commentRequest).enqueue(callback)
    }

    fun postLike(
        token: String,
        likeRequest: LikeRequest,
        callback: Callback<LikeResponse>
    ) {
        api.postLike("Bearer $token", likeRequest).enqueue(callback)
    }

    //--------------------------Eddig működöttek---------------------------------

}