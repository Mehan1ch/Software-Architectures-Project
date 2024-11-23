package hu.bme.aut.android.writer_reader_client.data.repository

import hu.bme.aut.android.writer_reader_client.data.model.post.CommentRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiManager(private val repository: ApiRepository) {

    fun postComment(
        token: String,
        commentRequest: CommentRequest,
        onSuccess: (CommentResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.postComment(token, commentRequest, object : Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError("Response body is null")
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                onError(t.message ?: "Network error")
            }
        })
    }

    fun postLike(
        token: String,
        likeRequest: LikeRequest,
        onSuccess: (LikeResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.postLike(token, likeRequest, object : Callback<LikeResponse> {
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError("Response body is null")
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                onError(t.message ?: "Network error")
            }
        })
    }
}