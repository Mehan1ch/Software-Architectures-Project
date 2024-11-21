<?php

use App\Http\Requests\Store\StoreCommentRequest;
use App\Models\Work;

test('request fails', function () {
    $storeCommentRequest = new StoreCommentRequest();

    $storeCommentRequest->merge([
        'content' => '', // content is required
        'commentable_type' => 'eafwfwf', // commentable_type must be valid
        'commentable_id' => 'invalid-uuid', // commentable_id must be valid uuid
    ]);

    $validator = validator($storeCommentRequest->all(), $storeCommentRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('content')
        ->and($validator->errors()->toArray())->toHaveKey('commentable_type')
        ->and($validator->errors()->toArray())->toHaveKey('commentable_id');
});

test('request passes', function () {
    $storeCommentRequest = new StoreCommentRequest();

    $work = Work::factory()->create();


    $storeCommentRequest->merge([
        'content' => 'Comment Content',
        'commentable_type' => $work->getMorphClass(),
        'commentable_id' => $work->id,
    ]);

    $validator = validator($storeCommentRequest->all(), $storeCommentRequest->rules());

    expect($validator->passes())->toBeTrue();
});
