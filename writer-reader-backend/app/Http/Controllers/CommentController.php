<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreCommentRequest;
use App\Http\Requests\Update\UpdateCommentRequest;
use App\Http\Resources\Collections\CommentCollection;
use App\Http\Resources\CommentResource;
use App\Models\Comment;
use Illuminate\Http\JsonResponse;

class CommentController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): CommentCollection
    {
        return new CommentCollection(Comment::paginate());
    }

    /**
     * Display the specified resource.
     */
    public function show(Comment $comment): CommentResource
    {
        return new CommentResource($comment);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreCommentRequest $request): CommentResource
    {
        $comment = $request->user()->comments()->create($request->validated());
        return new CommentResource($comment);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateCommentRequest $request, Comment $comment): CommentResource
    {
        $comment->update($request->validated());
        return new CommentResource($comment);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Comment $comment): JsonResponse
    {
        $comment->delete();
        return response()->json(['message' => 'Comment deleted successfully.'], 200);
    }
}
