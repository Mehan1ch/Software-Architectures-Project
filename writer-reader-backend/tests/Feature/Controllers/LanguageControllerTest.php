<?php

use App\Models\Language;

test('get languages', function () {
    $response = $this->get('/api/languages');

    $response->assertStatus(200);
});

test('get a language', function () {
    $languageId = Language::factory()->create()->id;
    $response = $this->get('/api/languages/'.$languageId);

    $response->assertStatus(200);
});
