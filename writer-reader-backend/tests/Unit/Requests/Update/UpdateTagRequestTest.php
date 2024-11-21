<?php

use App\Http\Requests\Update\UpdateTagRequest;

test('request fails', function () {
    $updateTagRequest = new UpdateTagRequest();

    $updateTagRequest->merge([
        'name' => '', // name is required
    ]);

    $validator = validator($updateTagRequest->all(), $updateTagRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('name');
});

test('request passes', function () {
    $updateTagRequest = new UpdateTagRequest();

    $updateTagRequest->merge([
        'name' => 'Tag Name',
    ]);

    $validator = validator($updateTagRequest->all(), $updateTagRequest->rules());

    expect($validator->passes())->toBeTrue();
});
