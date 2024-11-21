<?php

use App\Enums\RolesEnum;
use App\Models\Comment;
use App\Models\User;
use App\Models\Work;

beforeEach(function () {
    // Only add the necessary role for the test
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});

test('get comments', function () {
    $response = $this->get('/api/comments');

    $response->assertStatus(200);
});

test('get a comment', function () {
    $commentId = Comment::factory()->create()->id;
    $response = $this->get('/api/comments/' . $commentId);
    $response->assertStatus(200);
});

test('create a comment', function () {
    $work = Work::factory()->create();
    $comment = [
        'content' => 'Comment Content',
        'commentable_type' => $work->getMorphClass(),
        'commentable_id' => $work->id,
    ];
    $response = $this->actingAs($this->user)->post('/api/comments', $comment);

    $response->assertCreated();
    $this->assertDatabaseHas('comments', [
        'id' => $response->json('data.id'),
        'content' => $comment['content'],
        'commentable_type' => $comment['commentable_type'],
        'commentable_id' => $comment['commentable_id'],
    ]);
});

test('update a comment', function () {
    $work = Work::factory()->create();
    $comment = Comment::factory([
        'commentable_type' => $work->getMorphClass(),
        'commentable_id' => $work->id,
        'user_id' => $this->user->id,
    ])->create();
    $newComment = [
        'content' => 'Updated Content',
        'commentable_type' => $work->getMorphClass(),
        'commentable_id' => $work->id,
    ];
    $response = $this->actingAs($this->user)->put('/api/comments/' . $comment->id, $newComment);

    $response->assertOk();
    $this->assertDatabaseHas('comments', [
        'id' => $comment->id,
        'content' => $newComment['content'],
        'commentable_type' => $newComment['commentable_type'],
        'commentable_id' => $newComment['commentable_id'],
    ]);
});

test('delete a comment', function () {
    $work = Work::factory()->create();
    $comment = Comment::factory([
        'commentable_type' => $work->getMorphClass(),
        'commentable_id' => $work->id,
        'user_id' => $this->user->id,
    ])->create();

    $response = $this->actingAs($this->user)->delete('/api/comments/' . $comment->id);

    $response->assertOk();
    $this->assertDatabaseMissing('comments', [
        'id' => $comment->id,
    ]);
});
