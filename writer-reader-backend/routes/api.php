<?php

use App\Http\Controllers\WorkController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::middleware(['auth:sanctum'])->get('/user', function (Request $request) {
    return $request->user();
});

// Don't put routes to the auth middleware just yet as it will complicate the testing process
Route::get('/works', [WorkController::class, 'index']);
Route::get('/works/{work}', [WorkController::class, 'show']);
