<?php

use App\Enums\PermissionsEnum;
use App\Enums\RolesEnum;
use App\Models\Rating;
use App\Models\User;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(RefreshDatabase::class);



beforeEach(function () {
    // Only add the necessary role for the test
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::MODERATOR->value);
    })->first();
});


test('get ratings', function () {
    $response = $this->get('/api/ratings');

    $response->assertOk();
});

test('get a single rating', function () {
    $ratingId = Rating::all()->random()->id;
    $response = $this->get('/api/ratings/'.$ratingId);

    $response->assertOk();
});

test('create a rating', function () {
    $rating = [
        'details' => 'Rating Details',
    ];
    $response = $this->actingAs($this->user)->post('/api/ratings', $rating);

    $response->assertCreated();
});


test('update a rating', function () {
    $rating = Rating::factory()->create();
    $newRating = [
        'details' => 'Update Rating Details',
    ];
    $response = $this->actingAs($this->user)->put('/api/ratings/'.$rating->id, $newRating);

    $response->assertOk();
    $this->assertDatabaseHas('ratings', [
        'id' => $rating->id,
        'details' => $newRating['details'],
    ]);
});

test('delete a rating', function () {
    $rating = Rating::factory()->create();
    $response = $this->actingAs($this->user)->delete('/api/ratings/'.$rating->id);

    $response->assertOk();
    $this->assertDatabaseMissing('ratings', [
        'id' => $rating->id,
    ]);
});
