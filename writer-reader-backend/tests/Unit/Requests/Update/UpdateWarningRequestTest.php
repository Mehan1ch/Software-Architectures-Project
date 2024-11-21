<?php

use App\Http\Requests\Update\UpdateWarningRequest;

test('request fails', function () {
    $updateWarningRequest = new UpdateWarningRequest();

    $updateWarningRequest->merge([
        'details' => '', // details is required
    ]);

    $validator = validator($updateWarningRequest->all(), $updateWarningRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('details');
});

test('request passes', function () {
    $updateWarningRequest = new UpdateWarningRequest();

    $updateWarningRequest->merge([
        'details' => 'Warning',
    ]);

    $validator = validator($updateWarningRequest->all(), $updateWarningRequest->rules());

    expect($validator->passes())->toBeTrue();
});
