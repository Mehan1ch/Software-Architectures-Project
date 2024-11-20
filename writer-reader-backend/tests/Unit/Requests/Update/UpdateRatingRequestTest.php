<?php

use App\Http\Requests\Update\UpdateRatingRequest;

test('request fails', function () {
  $updateRatingRequest = new UpdateRatingRequest();

    $updateRatingRequest->merge([
        'details' => '', // rating is required
    ]);

    $validator = validator($updateRatingRequest->all(), $updateRatingRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('details');
});

test('request passes', function () {
    $updateRatingRequest = new UpdateRatingRequest();

    $updateRatingRequest->merge([
        'details' => 'Rating Details',
    ]);

    $validator = validator($updateRatingRequest->all(), $updateRatingRequest->rules());

    expect($validator->passes())->toBeTrue();
});
