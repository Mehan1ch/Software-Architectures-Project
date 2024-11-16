<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreWarningRequest;
use App\Http\Requests\Update\UpdateWarningRequest;
use App\Http\Resources\WarningResource;
use App\Models\Warning;

class WarningController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return WarningResource::collection(Warning::paginate(10));
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreWarningRequest $request)
    {
        $warning = Warning::create($request->validated());
        return new WarningResource($warning);
    }

    /**
     * Display the specified resource.
     */
    public function show(Warning $warning)
    {
        return new WarningResource($warning);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateWarningRequest $request, Warning $warning)
    {
        $warning->update($request->validated());
        return new WarningResource($warning);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Warning $warning)
    {
        $warning->delete();
        return response()->json(['message' => 'Warning deleted successfully.'], 200);
    }
}
