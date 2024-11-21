<?php

use App\Http\Requests\Store\StoreLikeRequest;
use App\Models\Work;

test('request fails', function () {
    $storeLikeRequest = new StoreLikeRequest();

    $storeLikeRequest->merge([
        'likeable_id' => '', // likeable_id is required
        'likeable_type' => '', // likeable_type is required
    ]);

    $validator = validator($storeLikeRequest->all(), $storeLikeRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKeys(['likeable_id', 'likeable_type']);
});

test('request passes', function () {
    $storeLikeRequest = new StoreLikeRequest();

    $work = Work::factory()->create();

    $storeLikeRequest->merge([
        'likeable_id' => $work->id,
        'likeable_type' => $work->getMorphClass(),
    ]);

    $validator = validator($storeLikeRequest->all(), $storeLikeRequest->rules());

    expect($validator->passes())->toBeTrue();
});
