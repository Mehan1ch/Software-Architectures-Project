<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreMessageRequest;
use App\Http\Requests\Update\UpdateMessageRequest;
use App\Http\Resources\Collections\MessageCollection;
use App\Http\Resources\MessageResource;
use App\Models\Message;
use Illuminate\Http\JsonResponse;

class MessageController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): MessageCollection
    {
        return new MessageCollection(Message::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreMessageRequest $request): MessageResource
    {
        $message = Message::create($request->validated());
        return new MessageResource($message);
    }

    /**
     * Display the specified resource.
     */
    public function show(Message $message): MessageResource
    {
        return new MessageResource($message);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateMessageRequest $request, Message $message): MessageResource
    {
        $message->update($request->validated());
        return new MessageResource($message);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Message $message): JsonResponse
    {
        $message->delete();
        return response()->json(['message' => 'Message deleted successfully.'], 200);
    }
}
