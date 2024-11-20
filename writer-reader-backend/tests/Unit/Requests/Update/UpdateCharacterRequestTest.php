<?php

use App\Http\Requests\Update\UpdateCharacterRequest;

test('request fails', function () {
    $updateCharacterRequest = new UpdateCharacterRequest();

    $updateCharacterRequest->merge([
        'name' => '', // name is required
    ]);

    $validator = validator($updateCharacterRequest->all(), $updateCharacterRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('name');
});

test('request passes', function () {
    $updateCharacterRequest = new UpdateCharacterRequest();

    $updateCharacterRequest->merge([
        'name' => 'Character Name',
    ]);

    $validator = validator($updateCharacterRequest->all(), $updateCharacterRequest->rules());

    expect($validator->passes())->toBeTrue();
});
