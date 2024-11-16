<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreCollectionRequest;
use App\Http\Requests\Update\UpdateCollectionRequest;
use App\Http\Resources\CollectionResource;
use App\Http\Resources\Collections\CollectionCollection;
use App\Models\Collection;
use Illuminate\Http\JsonResponse;

class CollectionController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): CollectionCollection
    {
        return new CollectionCollection(Collection::paginate());
    }


    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreCollectionRequest $request): CollectionResource
    {
        $collection = Collection::create($request->validated());
        return new CollectionResource($collection);
    }

    /**
     * Display the specified resource.
     */
    public function show(Collection $collection): CollectionResource
    {
        return new CollectionResource($collection);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateCollectionRequest $request, Collection $collection): CollectionResource
    {
        $collection->update($request->validated());
        return new CollectionResource($collection);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Collection $collection): JsonResponse
    {
        $collection->delete();
        return response()->json(['message' => 'Collection deleted successfully.'], 200);
    }
}
