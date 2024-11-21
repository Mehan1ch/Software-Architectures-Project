<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreChapterRequest;
use App\Http\Requests\Update\UpdateChapterRequest;
use App\Http\Resources\ChapterResource;
use App\Http\Resources\Collections\ChapterCollection;
use App\Models\Chapter;
use Illuminate\Http\JsonResponse;

class ChapterController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): ChapterCollection
    {
        return new ChapterCollection(Chapter::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreChapterRequest $request): ChapterResource
    {
        $chapter = Chapter::create($request->validated());
        return new ChapterResource($chapter);
    }

    /**
     * Display the specified resource.
     */
    public function show(Chapter $chapter): ChapterResource
    {
        return new ChapterResource($chapter);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateChapterRequest $request, Chapter $chapter): ChapterResource
    {
        $chapter->update($request->validated());
        return new ChapterResource($chapter);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Chapter $chapter): JsonResponse
    {
        $chapter->delete();
        return response()->json(['message' => 'Chapter deleted successfully.'], 200);
    }
}
