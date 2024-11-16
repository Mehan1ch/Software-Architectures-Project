<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreWorkRequest;
use App\Http\Requests\Update\UpdateWorkRequest;
use App\Http\Resources\Collections\WorkCollection;
use App\Http\Resources\WorkResource;
use App\Models\Work;
use Illuminate\Http\JsonResponse;

class WorkController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): WorkCollection
    {
        return new WorkCollection(Work::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreWorkRequest $request): WorkResource
    {
        $work = Work::create($request->validated());
        return new WorkResource($work);
    }

    /**
     * Display the specified resource.
     */
    public function show(Work $work): WorkResource
    {
        return new WorkResource($work);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateWorkRequest $request, Work $work): WorkResource
    {
        $work->update($request->validated());
        return new WorkResource($work);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Work $work): JsonResponse
    {
        $work->delete();
        return response()->json(['message' => 'Work deleted successfully.'], 200);
    }
}
