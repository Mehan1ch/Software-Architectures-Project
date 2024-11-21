<?php

use App\Http\Requests\Store\StoreRatingRequest;

test('request fails', function () {
    $storeRatingRequest = new StoreRatingRequest();

    $storeRatingRequest->merge([
        'details' => '', // details is required
    ]);

    $validator = validator($storeRatingRequest->all(), $storeRatingRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('details');
});


test('request passes', function () {
    $storeRatingRequest = new StoreRatingRequest();

    $storeRatingRequest->merge([
        'details' => 'Rating Details',
    ]);

    $validator = validator($storeRatingRequest->all(), $storeRatingRequest->rules());

    expect($validator->passes())->toBeTrue();
});
