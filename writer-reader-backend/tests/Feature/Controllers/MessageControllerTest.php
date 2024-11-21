<?php


use App\Enums\RolesEnum;
use App\Models\Message;
use App\Models\User;



beforeEach(function () {
    // Only add the necessary role for the test
    $this->sent_by = User::factory(1)->create()->each(function ($sent_by) {
        $sent_by->assignRole(RolesEnum::REGISTERED->value);
    })->first();
    $this->sent_to = User::factory(1)->create()->each(function ($sent_to) {
        $sent_to->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});

test('get messages', function () {
    $response = $this->actingAs($this->sent_by)->get('/api/messages');

    $response->assertOk();
});

test('get a single message', function () {
    $message = Message::factory()->create([
        'sent_to_id' => $this->sent_to->id,
        'sent_by_id' => $this->sent_by->id,
    ]);
    $response = $this->actingAs($this->sent_by)->get('/api/messages/'.$message->id);

    $response->assertOk();
});

test('create a message', function () {
    $message = [
        'content' => 'Message content',
        'sent_by_id' => $this->sent_by->id,
        'sent_to_id' => $this->sent_to->id,

    ];
    $response = $this->actingAs($this->sent_by)->post('/api/messages', $message);

    $response->assertCreated();
});


test('update a message', function () {
    $message = Message::factory([
        'sent_by_id' => $this->sent_by->id,
        'sent_to_id' => $this->sent_to->id,
    ])->create();
    $newMessage = [
        'content' => 'Updated Message content',
        'sent_by_id' => $this->sent_by->id,
        'sent_to_id' => $this->sent_to->id,
    ];
    $response = $this->actingAs($this->sent_by)->put('/api/messages/'.$message->id, $newMessage);

    $response->assertOk();
    $this->assertDatabaseHas('messages', [
        'id' => $message->id,
        'content' => $newMessage['content'],
        'sent_by_id' => $newMessage['sent_by_id'],
        'sent_to_id' => $newMessage['sent_to_id'],
    ]);
});

test('delete a message', function () {
    $message = Message::factory([
        'sent_by_id' => $this->sent_by->id,
        'sent_to_id' => $this->sent_to->id,
    ])->create();
    $response = $this->actingAs($this->sent_by)->delete('/api/messages/'.$message->id);

    $response->assertOk();
    $this->assertDatabaseMissing('messages', [
        'id' => $message->id,
    ]);
});
