<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreRatingRequest;
use App\Http\Requests\Update\UpdateRatingRequest;
use App\Http\Resources\Collections\RatingCollection;
use App\Http\Resources\RatingResource;
use App\Models\Rating;

class RatingController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): RatingCollection
    {
        return new RatingCollection(Rating::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreRatingRequest $request): RatingResource
    {
        $rating = Rating::create($request->validated());
        return new RatingResource($rating);
    }

    /**
     * Display the specified resource.
     */
    public function show(Rating $rating): RatingResource
    {
        return new RatingResource($rating);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateRatingRequest $request, Rating $rating): RatingResource
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
