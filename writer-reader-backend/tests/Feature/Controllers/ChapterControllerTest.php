<?php

use App\Enums\RolesEnum;
use App\Models\Chapter;
use App\Models\User;
use App\Models\Work;

beforeEach(function () {
    // Only add the necessary role for the test
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});

test('get chapters', function () {
    $response = $this->get('/api/chapters');

    $response->assertOk();
});

test('get chapter', function () {
    $chapterId = Chapter::all()->random()->id;
    $response = $this->get('/api/chapters/' . $chapterId);

    $response->assertOk();
});

test('post chapter', function () {
    $work = Work::factory([
        'user_id' => $this->user->id,
    ])->create();
    $chapter = [
        'title' => 'Chapter Title',
        'content' => 'Chapter Content',
        'work_id' => $work->id,
    ];
    $response = $this->actingAs($this->user)->post('/api/chapters', $chapter);

    $response->assertCreated();
    $this->assertDatabaseHas('chapters', [
        'id'=>$response->json('data.id'),
        'title' => $chapter['title'],
        'content' => $chapter['content'],
        'work_id' => $work->id,
    ]);
});

test('update chapter', function () {
    $work = Work::factory([
        'user_id' => $this->user->id,
    ])->create();
    $chapter = Chapter::factory([
        'work_id' => $work->id,
    ])->create();
    $newChapter = [
        'title' => 'Updated Title',
        'content' => 'Updated Content',
        'work_id' => $work->id,
    ];
    $chapterId = $chapter->id;

    $response = $this->actingAs($this->user)->put('/api/chapters/' . $chapterId, $newChapter);
    $response->assertOk();
    $this->assertDatabaseHas('chapters', [
        'title' => $newChapter['title'],
        'content' => $newChapter['content'],
        'work_id' => $work->id,
    ]);

});

test('delete chapter', function () {
    $work = Work::factory([
        'user_id' => $this->user->id,
    ])->create();
    $chapter = Chapter::factory([
        'work_id' => $work->id,
    ])->create();
    $response = $this->actingAs($this->user)->delete('/api/chapters/' . $chapter->id);

    $response->assertOk();
    $this->assertDatabaseMissing('chapters', [
        'id' => $chapter->id,
    ]);
});
