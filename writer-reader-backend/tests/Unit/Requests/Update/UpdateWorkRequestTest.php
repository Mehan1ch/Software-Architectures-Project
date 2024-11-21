<?php

use App\Enums\ModerationEnum;
use App\Http\Requests\Update\UpdateWorkRequest;
use App\Models\Category;
use App\Models\Character;
use App\Models\Language;
use App\Models\Rating;
use App\Models\Tag;
use App\Models\User;
use App\Models\Warning;

test('request fails', function () {
    $updateWorkRequest = new UpdateWorkRequest();

    $updateWorkRequest->merge([
        'title' => '', // title is required
        'content' => '', // content is required
        'moderation_status' => '', // moderation_status is required
        'moderator_id' => 'invalid-uuid', // moderator_id should be a valid uuid
        'rating_id' => 'invalid-uuid', // rating_id should be a valid uuid
        'language_id' => 'invalid-uuid', // language_id should be a valid uuid
        'warnings' => ['invalid-uuid'], // warnings should be an array of valid uuids
        'tags' => ['invalid-uuid'], // tags should be an array of valid uuids
        'characters' => ['invalid-uuid'], // characters should be an array of valid uuids
        'categories' => ['invalid-uuid'], // categories should be an array of valid uuids
    ]);

    $validator = validator($updateWorkRequest->all(), $updateWorkRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('title')
        ->and($validator->errors()->toArray())->toHaveKey('content')
        ->and($validator->errors()->toArray())->toHaveKey('moderation_status')
        ->and($validator->errors()->toArray())->toHaveKey('moderator_id')
        ->and($validator->errors()->toArray())->toHaveKey('rating_id')
        ->and($validator->errors()->toArray())->toHaveKey('language_id')
        ->and($validator->errors()->toArray())->toHaveKey('warnings.0')
        ->and($validator->errors()->toArray())->toHaveKey('tags.0')
        ->and($validator->errors()->toArray())->toHaveKey('characters.0')
        ->and($validator->errors()->toArray())->toHaveKey('categories.0');
});

test('request passes', function () {
    $updateWorkRequest = new UpdateWorkRequest();

    $updateWorkRequest->merge([
        'title' => 'Work Title',
        'content' => 'Work Content',
        'moderation_status' => ModerationEnum::APPROVED->value,
        'moderator_id' => User::factory()->create()->id,
        'rating_id' => Rating::factory()->create()->id,
        'language_id' => Language::factory()->create()->id,
        'warnings' => Warning::factory(3)->create()->pluck('id')->toArray(),
        'tags' => Tag::factory(3)->create()->pluck('id')->toArray(),
        'characters' => Character::factory(3)->create()->pluck('id')->toArray(),
        'categories' => Category::factory(3)->create()->pluck('id')->toArray(),
    ]);

    $validator = validator($updateWorkRequest->all(), $updateWorkRequest->rules());

    expect($validator->passes())->toBeTrue();
});
