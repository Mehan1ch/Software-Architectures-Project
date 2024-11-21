<?php

use App\Http\Requests\Store\StoreCollectionRequest;
use App\Models\Work;

test('request fails', function () {
    $storeCollectionRequest = new StoreCollectionRequest();

    $storeCollectionRequest->merge([
        'name' => '', // name is required
        'description' => '', // description is required
        'works' => [
            'adawwdad', // works must be an array of ids of works
            'waddwda'
        ]
    ]);

    $validator = validator($storeCollectionRequest->all(), $storeCollectionRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKeys(['name', 'description', 'works.0', 'works.1']);
});

test('request passes', function () {
    $storeCollectionRequest = new StoreCollectionRequest();

    $workIds = Work::factory(2)->create()->pluck('id')->toArray();

    $storeCollectionRequest->merge([
        'name' => 'Collection Name',
        'description' => 'Collection Description',
        'works' => $workIds
    ]);

    $validator = validator($storeCollectionRequest->all(), $storeCollectionRequest->rules());

    expect($validator->passes())->toBeTrue();
});
