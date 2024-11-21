<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreCharacterRequest;
use App\Http\Requests\Update\UpdateCharacterRequest;
use App\Http\Resources\CharacterResource;
use App\Http\Resources\Collections\CharacterCollection;
use App\Models\Character;
use Illuminate\Http\JsonResponse;

class CharacterController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): CharacterCollection
    {
        return new CharacterCollection(Character::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreCharacterRequest $request): CharacterResource
    {
        $character = $request->user()->characters()->create($request->validated());
        return new CharacterResource($character);
    }

    /**
     * Display the specified resource.
     */
    public function show(Character $character): CharacterResource
    {
        return new CharacterResource($character);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateCharacterRequest $request, Character $character): CharacterResource
    {
        $character->update($request->validated());
        return new CharacterResource($character);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Character $character): JsonResponse
    {
        $character->delete();
        return response()->json(['message' => 'Character deleted successfully.'], 200);
    }
}
