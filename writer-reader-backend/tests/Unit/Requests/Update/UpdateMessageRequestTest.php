<?php

use App\Http\Requests\Update\UpdateMessageRequest;
use App\Models\User;

test('request fails', function () {
    $updateMessageRequest = new UpdateMessageRequest();

    $updateMessageRequest->merge([
        'content' => '', // content is required
        'sent_to_id' => '', // sent_to_id is required
    ]);

    $validator = validator($updateMessageRequest->all(), $updateMessageRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKeys(['content', 'sent_to_id']);
});

test('request passes', function () {
    $updateMessageRequest = new UpdateMessageRequest();

    $user = User::factory()->create();

    $updateMessageRequest->merge([
        'content' => 'Message Content',
        'sent_to_id' => $user->id,
    ]);

    $validator = validator($updateMessageRequest->all(), $updateMessageRequest->rules());

    expect($validator->passes())->toBeTrue();

});
