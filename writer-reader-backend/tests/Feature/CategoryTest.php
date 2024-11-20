<?php

use App\Enums\RolesEnum;
use App\Models\Category;
use App\Models\User;

beforeEach(function () {
    // Only add the necessary role for the test
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::MODERATOR->value);
    })->first();
});


test('get categories', function () {
    $response = $this->get('/api/categories');

    $response->assertOk();
    // alternative $response->assertStatus(200);
});

test('get a single category', function () {
    $categoryId = Category::all()->random()->id;
    $response = $this->get('/api/categories/'.$categoryId);

    $response->assertOk();
});

test('create a category', function () {
    $category = [
        'name' => 'Category Name',
        'description' => 'Category Description',
    ];
    $response = $this->actingAs($this->user)->post('/api/categories', $category);

    $response->assertCreated();
    $this->assertDatabaseHas('categories', [
        'name' => $category['name'],
        'description' => $category['description'],
    ]);
});


test('update a category', function () {
    $category = Category::factory()->create();
    $newCategory = [
        'name' => 'Updated Name',
        'description' => 'Updated Description',
    ];
    $response = $this->actingAs($this->user)->put('/api/categories/'.$category->id, $newCategory);

    $response->assertOk();
    $this->assertDatabaseHas('categories', [
        'id' => $category->id,
        'name' => $newCategory['name'],
        'description' => $newCategory['description'],
    ]);
});

test('delete a category', function () {
    $category = Category::factory()->create();
    $response = $this->actingAs($this->user)->delete('/api/categories/'.$category->id);

    $response->assertOk();
    $this->assertDatabaseMissing('categories', [
        'id' => $category->id,
    ]);
});
