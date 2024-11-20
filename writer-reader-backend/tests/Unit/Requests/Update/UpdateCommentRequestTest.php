<?php

use App\Http\Requests\Update\UpdateCommentRequest;
use App\Models\Collection;

test('request fails', function () {
    $updateCommentRequest = new UpdateCommentRequest();

    $updateCommentRequest->merge([
        'content' => '', // content is required
        'commentable_type' => '', // commentable_type is required
        'commentable_id' => '', // commentable_id is required
    ]);

    $validator = validator($updateCommentRequest->all(), $updateCommentRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKeys(['content', 'commentable_type', 'commentable_id']);
});

test('request passes', function () {
    $updateCommentRequest = new UpdateCommentRequest();

    $collection = Collection::factory()->create();

    $updateCommentRequest->merge([
        'content' => 'Comment Content',
        'commentable_type' => $collection->getMorphClass(),
        'commentable_id' => $collection->id,
    ]);

    $validator = validator($updateCommentRequest->all(), $updateCommentRequest->rules());

    expect($validator->passes())->toBeTrue();
});
