<?php

use App\Http\Requests\Update\UpdateLikeRequest;
use App\Models\Comment;

test('request fails', function () {
    $updateLikeRequest = new UpdateLikeRequest();

    $updateLikeRequest->merge([
        'likeable_type' => 'eafwfwf', // likeable_type must be valid
        'likeable_id' => 'invalid-uuid', // likeable_id must be valid uuid
    ]);

    $validator = validator($updateLikeRequest->all(), $updateLikeRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('likeable_type')
        ->and($validator->errors()->toArray())->toHaveKey('likeable_id');
});

test('request passes', function () {
    $updateLikeRequest = new UpdateLikeRequest();

    $comment = Comment::factory()->create();

    $updateLikeRequest->merge([
        'likeable_type' => $comment->getMorphClass(),
        'likeable_id' => $comment->id
    ]);

    $validator = validator($updateLikeRequest->all(), $updateLikeRequest->rules());

    expect($validator->passes())->toBeTrue();
});
