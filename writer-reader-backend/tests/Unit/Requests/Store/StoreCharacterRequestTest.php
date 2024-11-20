<?php

use App\Http\Requests\Store\StoreCharacterRequest;

test('request fails', function () {
    $storeCharacterRequest = new StoreCharacterRequest();

    $storeCharacterRequest->merge([
        'name' => '', // name is required
    ]);

    $validator = validator($storeCharacterRequest->all(), $storeCharacterRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('name');
});

test('request passes', function () {
    $storeCharacterRequest = new StoreCharacterRequest();

    $storeCharacterRequest->merge([
        'name' => 'Character Name',
    ]);

    $validator = validator($storeCharacterRequest->all(), $storeCharacterRequest->rules());

    expect($validator->passes())->toBeTrue();
});
