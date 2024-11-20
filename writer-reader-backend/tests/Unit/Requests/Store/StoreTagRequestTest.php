<?php

use App\Http\Requests\Store\StoreTagRequest;

test('request fails', function () {
    $storeTagRequest = new StoreTagRequest();

    $storeTagRequest->merge([
        'name' => '', // name is required
    ]);

    $validator = validator($storeTagRequest->all(), $storeTagRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('name');
});

test('request passes', function () {
    $storeTagRequest = new StoreTagRequest();

    $storeTagRequest->merge([
        'name' => 'Tag Name',
    ]);

    $validator = validator($storeTagRequest->all(), $storeTagRequest->rules());

    expect($validator->passes())->toBeTrue();
});
