<?php

use App\Http\Requests\Store\StoreMessageRequest;
use App\Models\User;

test('request fails', function () {
    $storeMessageRequest = new StoreMessageRequest();

    $storeMessageRequest->merge([
        'content' => '', // message is required
        'sent_to_id' => '', // sent_to_id is required
    ]);

    $validator = validator($storeMessageRequest->all(), $storeMessageRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKeys(['content', 'sent_to_id']);
});

test('request passes', function () {
    $storeMessageRequest = new StoreMessageRequest();

    $user = User::factory()->create();

    $storeMessageRequest->merge([
        'content' => 'Message Content',
        'sent_to_id' => $user->id,
    ]);

    $validator = validator($storeMessageRequest->all(), $storeMessageRequest->rules());

    expect($validator->passes())->toBeTrue();
});
