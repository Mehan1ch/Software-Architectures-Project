<?php

use App\Enums\RolesEnum;
use App\Models\Category;
use App\Models\Character;
use App\Models\Language;
use App\Models\Rating;
use App\Models\Tag;
use App\Models\User;
use App\Models\Warning;
use App\Models\Work;
use Illuminate\Foundation\Testing\RefreshDatabase;

beforeEach(function () {
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
    $this->moderator = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::MODERATOR->value);
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
        'title' => 'Work Title',
        'content' => 'Work Content',
        'moderator_id' => $this->moderator->id,
        'rating_id' => Rating::factory()->create()->id,
        'language_id' => Language::factory()->create()->id,
        'warnings' => Warning::factory(3)->create()->pluck('id')->toArray(),
        'tags' => Tag::factory(3)->create()->pluck('id')->toArray(),
        'characters' => Character::factory(3)->create()->pluck('id')->toArray(),
        'categories' => Category::factory(3)->create()->pluck('id')->toArray(),
    ];

    $response = $this->actingAs($this->user)->post('/api/works', $workData);

    $response->assertCreated();
    $this->assertDatabaseHas('works', [
        'title' => $workData['title'],
        'content' => $workData['content'],
        'moderator_id' => $workData['moderator_id'],
        'rating_id' => $workData['rating_id'],
        'language_id' => $workData['language_id'],
        'user_id' => $this->user->id,
    ]);
});

test('update a work', function () {
    $work = Work::factory([
        'user_id' => $this->user->id,
    ])->create();

    $newData = [
        'title' => 'Updated Work',
        'content' => 'Updated content.',
        'moderation_status' => $work->moderation_status->value,
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
    $work = Work::factory([
        'user_id' => $this->user->id,
    ])->create();

    $response = $this->actingAs($this->user)->delete('/api/works/' . $work->id);

    $response->assertOk();
    $this->assertDatabaseMissing('works', [
        'id' => $work->id,
    ]);
});
