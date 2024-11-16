<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreLikeRequest;
use App\Http\Requests\Update\UpdateLikeRequest;
use App\Http\Resources\LikeResource;
use App\Models\Like;

class LikeController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return LikeResource::collection(Like::paginate(10));
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreLikeRequest $request)
    {
        $like = Like::create($request->validated());
        return new LikeResource($like);
    }

    /**
     * Display the specified resource.
     */
    public function show(Like $like)
    {
        return new LikeResource($like);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateLikeRequest $request, Like $like)
    {
        $like->update($request->validated());
        return new LikeResource($like);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Like $like)
    {
        $like->delete();
        return response()->json(['message' => 'Like deleted successfully.'], 200);
    }
}
