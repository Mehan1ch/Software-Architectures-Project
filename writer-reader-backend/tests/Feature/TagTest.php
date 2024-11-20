<?php

use App\Enums\RolesEnum;
use App\Models\Tag;
use App\Models\User;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(RefreshDatabase::class);



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
    $tagId = Tag::all()->random()->id;
    $response = $this->get('/api/tags/'.$tagId);

    $response->assertOk();
});

test('create a tag', function () {
    $userId = $this->user->id;
    $tag = [
        'name' => 'Tag Name',
        'user_id' => $userId,
    ];
    $response = $this->actingAs($this->user)->post('/api/tags', $tag);

    $response->assertCreated();
    $this->assertDatabaseHas('tags', [
        'name' => $tag['name'],
        'user_id' => $userId,
    ]);
});


test('update a tag', function () {
    $tag = Tag::factory()->create(['user_id' => $this->user->id]);
    $newTag = [
        'name' => 'Update Tag Name',
        'user_id' => $this->user->id,
    ];
    $response = $this->actingAs($this->user)->put('/api/tags/'.$tag->id, $newTag);

    $response->assertOk();
    $this->assertDatabaseHas('tags', [
        'name' => $newTag['name'],
        'user_id' => $this->user->id,
    ]);
});

test('delete a tag', function () {
    $tag = Tag::factory()->create(['user_id' => $this->user->id]);
    $response = $this->actingAs($this->user)->delete('/api/tags/'.$tag->id);

    $response->assertOk();
    $this->assertDatabaseMissing('tags', [
        'id' => $tag->id,
    ]);
});
