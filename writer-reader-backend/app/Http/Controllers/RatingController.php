<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreRatingRequest;
use App\Http\Requests\Update\UpdateRatingRequest;
use App\Http\Resources\RatingResource;
use App\Models\Rating;

class RatingController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return RatingResource::collection(Rating::paginate(10));
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreRatingRequest $request)
    {
        $rating = Rating::create($request->validated());
        return new RatingResource($rating);
    }

    /**
     * Display the specified resource.
     */
    public function show(Rating $rating)
    {
        return new RatingResource($rating);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateRatingRequest $request, Rating $rating)
    {
        $rating->update($request->validated());
        return new RatingResource($rating);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Rating $rating)
    {
        $rating->delete();
        return response()->json(['message' => 'Rating deleted successfully.'], 200);
    }
}
