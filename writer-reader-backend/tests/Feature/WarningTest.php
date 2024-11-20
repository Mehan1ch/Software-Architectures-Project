<?php

use App\Enums\RolesEnum;
use App\Models\User;
use App\Models\Warning;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(RefreshDatabase::class);

beforeEach(function () {
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::MODERATOR->value);
    })->first();
});

test('get warnings', function () {
    $response = $this->get('/api/warnings');

    $response->assertOk();
});

test('get a single warning', function () {
    $warningId = Warning::all()->random()->id;
    $response = $this->get('/api/warnings/' . $warningId);

    $response->assertOk();
});

test('create a warning', function () {
    $warningData = [
        'details' => 'Warning Details',
    ];

    $response = $this->actingAs($this->user)->post('/api/warnings', $warningData);

    $response->assertCreated();
    $this->assertDatabaseHas('warnings', [
        'details' => $warningData['details'],
    ]);
});

test('update a warning', function () {
    $warning = Warning::factory()->create();
    $newData = [
        'details' => 'Update Warning Details',
    ];

    $response = $this->actingAs($this->user)->put('/api/warnings/' . $warning->id, $newData);

    $response->assertOk();
    $this->assertDatabaseHas('warnings', [
        'id' => $warning->id,
        'details' => $newData['details'],
    ]);
});

test('delete a warning', function () {
    $warning = Warning::factory()->create();

    $response = $this->actingAs($this->user)->delete('/api/warnings/' . $warning->id);

    $response->assertOk();
    $this->assertDatabaseMissing('warnings', [
        'id' => $warning->id,
    ]);
});
