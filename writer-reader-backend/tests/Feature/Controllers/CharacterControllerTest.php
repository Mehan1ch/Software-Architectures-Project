<?php

use App\Enums\RolesEnum;
use App\Models\Character;
use App\Models\User;


beforeEach(function () {
    // Only add the necessary role for the test
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});

test('get characters', function () {
    $response = $this->get('/api/characters');

    $response->assertOk();
});

test('get a character', function () {
    $characterId = Character::factory()->create()->id;
    $response = $this->get('/api/characters/' . $characterId);
    $response->assertOk();
});


test('create a character', function () {
    $character = [
        'name' => 'Character Name',
    ];
    $response = $this->actingAs($this->user)->post('/api/characters', $character);

    $response->assertCreated();
    $this->assertDatabaseHas('characters', [
        'id' => $response->json('data.id'),
        'name' => $character['name'],
    ]);
});

test('update a character', function () {
    $character = Character::factory([
        'user_id' => $this->user->id,
    ])->create();
    $newCharacter = [
        'name' => 'Updated Name',
    ];
    $response = $this->actingAs($this->user)->put('/api/characters/' . $character->id, $newCharacter);

    $response->assertOk();
    $this->assertDatabaseHas('characters', [
        'id' => $character->id,
        'name' => $newCharacter['name'],
    ]);
});

test('delete a character', function () {
    $character = Character::factory([
        'user_id' => $this->user->id,
    ])->create();

    $response = $this->actingAs($this->user)->delete('/api/characters/' . $character->id);

    $response->assertOk();
    $this->assertDatabaseMissing('characters', [
        'id' => $character->id,
    ]);
});
