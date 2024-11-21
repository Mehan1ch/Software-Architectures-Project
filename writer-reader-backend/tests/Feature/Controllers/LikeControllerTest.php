<?php

use App\Enums\RolesEnum;
use App\Models\Collection;
use App\Models\Like;
use App\Models\User;

beforeEach(function () {
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});

test('get likes', function () {
    $response = $this->get('/api/likes');

    $response->assertStatus(200);
});

test('get a like', function () {
    $likeId = App\Models\Like::factory()->create()->id;
    $response = $this->get('/api/likes/' . $likeId);

    $response->assertStatus(200);
});

test('create a like', function () {
    $work = App\Models\Work::factory()->create();
    $like = [
        'likeable_id' => $work->id,
        'likeable_type' => $work->getMorphClass(),
    ];

    $response = $this->actingAs($this->user)->post('/api/likes', $like);

    $response->assertCreated();
    $this->assertDatabaseHas('likes', [
        'id' => $response->json('data.id'),
        'likeable_id' => $like['likeable_id'],
        'likeable_type' => $like['likeable_type'],
        'user_id' => $this->user->id,
    ]);
});

test('update a like', function () {
    $like = Like::factory([
        'user_id' => $this->user->id,
    ])->create();

    $collection = Collection::factory()->create();

    $newLike = [
        'likeable_id' => $collection->id,
        'likeable_type' => $collection->getMorphClass(),
    ];

    $response = $this->actingAs($this->user)->put('/api/likes/' . $like->id, $newLike);

    $response->assertOk();

    $this->assertDatabaseHas('likes', [
        'id' => $like->id,
        'likeable_id' => $newLike['likeable_id'],
        'likeable_type' => $newLike['likeable_type'],
        'user_id' => $this->user->id,
    ]);
});

test('delete a like', function () {
    $like = Like::factory([
        'user_id' => $this->user->id,
    ])->create();

    $response = $this->actingAs($this->user)->delete('/api/likes/' . $like->id);

    $response->assertOk();
    $this->assertDatabaseMissing('likes', [
        'id' => $like->id,
    ]);
});
