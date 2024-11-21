<?php

use App\Http\Requests\Store\StoreChapterRequest;
use App\Models\Work;

test('request fails', function () {
    $storeChapterRequest= new StoreChapterRequest();

    $storeChapterRequest->merge([
        'title' => '', // title is required
        'content' => 1, // content should be a string
        'work_id' => 'invalid-uuid', // work_id should be a valid uuid
    ]);

    $validator = validator($storeChapterRequest->all(), $storeChapterRequest->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('title')
        ->and($validator->errors()->toArray())->toHaveKey('content')
        ->and($validator->errors()->toArray())->toHaveKey('work_id');
});

test('request passes', function(){
   $storeChapterRequest = new StoreChapterRequest();

   $work = Work::factory()->create();

   $storeChapterRequest->merge([
       'title' => 'Chapter Title',
       'content' => 'Chapter Content',
       'work_id' => $work->id
   ]);

   $validator = validator($storeChapterRequest->all(), $storeChapterRequest->rules());

    expect($validator->passes())->toBeTrue();
});
