<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreCharacterRequest;
use App\Http\Requests\Update\UpdateCharacterRequest;
use App\Http\Resources\CharacterResource;
use App\Models\Character;

class CharacterController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return CharacterResource::collection(Character::paginate(10));
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreCharacterRequest $request)
    {
        $character = Character::create($request->validated());
        return new CharacterResource($character);
    }

    /**
     * Display the specified resource.
     */
    public function show(Character $character)
    {
        return new CharacterResource($character);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateCharacterRequest $request, Character $character)
    {
        $character->update($request->validated());
        return new CharacterResource($character);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Character $character)
    {
        $character->delete();
        return response()->json(['message' => 'Character deleted successfully.'], 200);
    }
}
