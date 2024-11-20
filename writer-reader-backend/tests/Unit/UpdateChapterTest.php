<?php

use App\Http\Requests\Update\UpdateChapterRequest;
use App\Models\Work;

test('request fails', function () {
    $updateChapterRequest = new UpdateChapterRequest();

    $updateChapterRequest->merge([
        'title' => '', // title is required
        'content' => 1, // content should be a string
        'work_id' => 'invalid-uuid', // work_id should be a valid uuid
    ]);

    $validator = validator($updateChapterRequest->all(), $updateChapterRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('title')
        ->and($validator->errors()->toArray())->toHaveKey('content')
        ->and($validator->errors()->toArray())->toHaveKey('work_id');
});

test('request passes', function () {
    $updateChapterRequest = new UpdateChapterRequest();

    $work = Work::factory()->create();

    $updateChapterRequest->merge([
        'title' => 'Chapter Title',
        'content' => 'Chapter Content',
        'work_id' => $work->id
    ]);

    $validator = validator($updateChapterRequest->all(), $updateChapterRequest->rules());

    expect($validator->passes())->toBeTrue();
});
