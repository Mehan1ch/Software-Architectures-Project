<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreTagRequest;
use App\Http\Requests\Update\UpdateTagRequest;
use App\Http\Resources\Collections\TagCollection;
use App\Http\Resources\TagResource;
use App\Models\Tag;
use Illuminate\Http\JsonResponse;

class TagController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): TagCollection
    {
        return new TagCollection(Tag::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreTagRequest $request): TagResource
    {
        $tag = $request->user()->tags()->create($request->validated());
        return new TagResource($tag);
    }

    /**
     * Display the specified resource.
     */
    public function show(Tag $tag): TagResource
    {
        return new TagResource($tag);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateTagRequest $request, Tag $tag): TagResource
    {
        $tag->update($request->validated());
        return new TagResource($tag);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Tag $tag): JsonResponse
    {
        $tag->delete();
        return response()->json(['message' => 'Tag deleted successfully.'], 200);
    }
}
