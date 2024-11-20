<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreLikeRequest;
use App\Http\Requests\Update\UpdateLikeRequest;
use App\Http\Resources\Collections\LikeCollection;
use App\Http\Resources\LikeResource;
use App\Models\Like;
use Illuminate\Http\JsonResponse;

class LikeController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): LikeCollection
    {
        return new LikeCollection(Like::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreLikeRequest $request): LikeResource
    {
        $like = $request->user()->likes()->create($request->validated());
        return new LikeResource($like);
    }

    /**
     * Display the specified resource.
     */
    public function show(Like $like): LikeResource
    {
        return new LikeResource($like);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateLikeRequest $request, Like $like): LikeResource
    {
        $like->update($request->validated());
        return new LikeResource($like);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Like $like): JsonResponse
    {
        $like->delete();
        return response()->json(['message' => 'Like deleted successfully.'], 200);
    }
}
