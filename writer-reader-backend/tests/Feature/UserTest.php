<?php

use App\Enums\RolesEnum;
use App\Models\User;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(RefreshDatabase::class);



beforeEach(function () {
    // Only add the necessary role for the test
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::MODERATOR->value);
    })->first();
});


test('get users', function () {
    $response = $this->get('/api/users');

    $response->assertOk();
});

test('get a single user', function () {
    //$userId = User::all()->random()->id;
    $response = $this->get('/api/users/'.$this->user->id);

    $response->assertOk();
});

test('create a user', function () {
    $user = [
        'name' => $this->user->name,
        'email' => $this->user->email,
        'password' => $this->user->password,
    ];
    $response = $this->actingAs($this->user)->post('/api/users', $user);

    $response->assertCreated();
});


test('update a user', function () {
    $newuser = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::MODERATOR->value);
    })->first();
    $newUser = [
        'name' => $newuser->name,
        'email' => $newuser->email,
        'password' => $newuser->password,
    ];
    $response = $this->actingAs($this->user)->put('/api/users/'.$newuser->id, $newUser);

    $response->assertOk();
    $this->assertDatabaseHas('users', [
        'id' => $newuser->id,
        'name' => $newUser['name'],
        'email' => $newUser['email'],
        'password' => $newUser['password'],
    ]);
});

test('delete a user', function () {
    $user = User::factory()->create(['user_id' => $this->user->id]);
    $response = $this->actingAs($this->user)->delete('/api/users/'.$user->id);

    $response->assertOk();
    $this->assertDatabaseMissing('users', [
        'id' => $user->id,
    ]);
});
