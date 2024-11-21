<?php

use App\Enums\RolesEnum;
use App\Models\Collection;
use App\Models\User;
use App\Models\Work;

beforeEach(function () {
    $this->user = User::factory(1)->create()->each(function ($user) {
        $user->assignRole(RolesEnum::REGISTERED->value);
    })->first();
});

test('get collection', function () {
    $response = $this->get('/api/collections');

    $response->assertStatus(200);
});

test('get a collection',function (){
    $collection = Collection::factory()->create();
    $response = $this->get('/api/collections/'. $collection->id);

    $response->assertStatus(200);

});

test('create a collection',function (){
    $workIds = Work::factory(3)->create()->pluck('id')->toArray();
    $collection = [
        'name' => 'Collection Name',
        'description' => 'Collection Description',
        'works' => $workIds,
    ];
    $response = $this->actingAs($this->user)->post('/api/collections', $collection);

    $response->assertCreated();
    $this->assertDatabaseHas('collections', [
        'id' => $response->json('data.id'),
        'name' => $collection['name'],
        'description' => $collection['description'],
    ]);
    $this->assertDatabaseHas('collection_work', [
        'collection_id' => $response->json('data.id'),
        'work_id' => $workIds[0],
    ]);
});

test('update a collection',function (){
    $collection = Collection::factory([
        'user_id' => $this->user->id,
    ])->create();
    $workIds = Work::factory(3)->create()->pluck('id')->toArray();
    $newCollection = [
        'name' => 'Updated Name',
        'description' => 'Updated Description',
        'works' => $workIds,
    ];
    $response = $this->actingAs($this->user)->put('/api/collections/' . $collection->id, $newCollection);

    $response->assertOk();
    $this->assertDatabaseHas('collections', [
        'id' => $collection->id,
        'name' => $newCollection['name'],
        'description' => $newCollection['description'],
    ]);
    $this->assertDatabaseHas('collection_work', [
        'collection_id' => $collection->id,
        'work_id' => $workIds[0],
    ]);

});

test('delete a collection',function (){
    $collection = Collection::factory([
        'user_id' => $this->user->id,
    ])->create();
    $response = $this->actingAs($this->user)->delete('/api/collections/' . $collection->id);

    $response->assertOk();
    $this->assertDatabaseMissing('collections', [
        'id' => $collection->id,
    ]);
    $this->assertDatabaseMissing('collection_work', [
        'collection_id' => $collection->id,
    ]);
});
