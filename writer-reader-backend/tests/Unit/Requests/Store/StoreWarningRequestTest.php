<?php

use App\Http\Requests\Store\StoreWarningRequest;

test('request fails', function () {
    $request = new StoreWarningRequest();

    $request->merge([
        'details' => '', // warning is required
    ]);

    $validator = validator($request->all(), $request->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('details');
});

test('request passes', function () {
    $request = new StoreWarningRequest();

    $request->merge([
        'details' => 'Warning Details',
    ]);

    $validator = validator($request->all(), $request->rules());

    expect($validator->passes())->toBeTrue();
});
