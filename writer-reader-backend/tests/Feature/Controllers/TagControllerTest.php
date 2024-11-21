<?php

use App\Enums\RolesEnum;
use App\Models\Tag;
use App\Models\User;

beforeEach(function () {
    // Only add the necessary role for the test
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});


test('get tags', function () {
    $response = $this->get('/api/tags');

    $response->assertOk();
});

test('get a single tag', function () {
    $tagId = Tag::factory()->create()->id;
    $response = $this->get('/api/tags/' . $tagId);

    $response->assertOk();
});

test('create a tag', function () {
    $tag = [
        'name' => 'Tag Name',
    ];
    $response = $this->actingAs($this->user)->post('/api/tags', $tag);

    $response->assertCreated();
    $this->assertDatabaseHas('tags', [
        'id' => $response->json('data.id'),
        'name' => $tag['name'],
        'user_id' => $this->user->id,
    ]);
});

test('update a tag', function () {

    $tag = Tag::factory([
        'user_id' => $this->user->id,
    ])->create();

    $newTag = [
        'name' => 'Update Tag Name',
    ];
    $response = $this->actingAs($this->user)->put('/api/tags/' . $tag->id, $newTag);

    $response->assertOk();
    $this->assertDatabaseHas('tags', [
        'id' => $tag->id,
        'name' => $newTag['name'],
        'user_id' => $this->user->id,
    ]);
});

test('delete a tag', function () {
    $tag = Tag::factory([
        'user_id' => $this->user->id
    ])->create();

    $response = $this->actingAs($this->user)->delete('/api/tags/' . $tag->id);

    $response->assertOk();
    $this->assertDatabaseMissing('tags', [
        'id' => $tag->id,
    ]);
});
