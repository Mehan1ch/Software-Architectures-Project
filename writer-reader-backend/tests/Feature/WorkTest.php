<?php

use App\Enums\RolesEnum;
use App\Models\User;
use App\Models\Work;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(RefreshDatabase::class);

beforeEach(function () {
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});

test('get works', function () {
    $response = $this->get('/api/works');

    $response->assertOk();
});

test('get a single work', function () {
    $work = Work::factory()->create();
    $response = $this->get('/api/works/' . $work->id);

    $response->assertOk();
});

test('create a work', function () {
    $workData = [
        'title' => 'Test Work',
        'content' => 'This is test content.',
        'user_id' => $this->user->id,
    ];

    $response = $this->actingAs($this->user)->post('/api/works', $workData);

    $response->assertCreated();
    $this->assertDatabaseHas('works', [
        'title' => $workData['title'],
        'content' => $workData['content'],
        'user_id' => $this->user->id,
    ]);
});

test('update a work', function () {
    $work = Work::factory()->create();
    $newData = [
        'title' => 'Updated Work',
        'content' => 'Updated content.',
        'user_id' => $this->user->id,
    ];

    $response = $this->actingAs($this->user)->put('/api/works/' . $work->id, $newData);

    $response->assertOk();
    $this->assertDatabaseHas('works', [
        'id' => $work->id,
        'title' => $newData['title'],
        'content' => $newData['content'],
        'user_id' => $this->user->id,
    ]);
});

test('delete a work', function () {
    $work = Work::factory()->create();

    $response = $this->actingAs($this->user)->delete('/api/works/' . $work->id);

    $response->assertOk();
    $this->assertDatabaseMissing('works', [
        'id' => $work->id,
    ]);
});
