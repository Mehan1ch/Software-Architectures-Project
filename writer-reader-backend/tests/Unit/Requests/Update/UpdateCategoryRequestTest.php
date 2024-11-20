<?php

use App\Http\Requests\Update\UpdateCategoryRequest;
use Illuminate\Support\Facades\Validator;

test('request fails', function () {
    $request = new UpdateCategoryRequest();

    $request->merge([
        'name' => '', // name is required
        'description' => 1, // description should be a string
    ]);

    $validator = Validator::make($request->all(), $request->rules());

    expect($validator->fails())->toBeTrue()
        ->and($validator->errors()->toArray())->toHaveKey('name')
        ->and($validator->errors()->toArray())->toHaveKey('description');
});

test('request passes', function (){
   $request = new UpdateCategoryRequest();

    $request->merge([
         'name' => 'Category Name',
         'description' => 'Category Description',
    ]);

    $validator = Validator::make($request->all(), $request->rules());

    expect($validator->passes())->toBeTrue();
});
