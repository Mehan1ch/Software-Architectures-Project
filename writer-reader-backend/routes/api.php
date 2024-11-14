<?php

use App\Http\Controllers\CategoryController;
use App\Http\Controllers\ChapterController;
use App\Http\Controllers\CollectionController;
use App\Http\Controllers\CommentController;
use App\Http\Controllers\LanguageController;
use App\Http\Controllers\LikeController;
use App\Http\Controllers\MessageController;
use App\Http\Controllers\RatingController;
use App\Http\Controllers\TagController;
use App\Http\Controllers\UserController;
use App\Http\Controllers\WarningController;
use App\Http\Controllers\WorkController;
use App\Http\Controllers\CharacterController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::middleware(['auth:sanctum'])->get('/user', function (Request $request) {
    return $request->user();
});

// Don't put routes to the auth middleware just yet as it will complicate the testing process
Route::get('/categories', [CategoryController::class, 'index']);
Route::get('/categories/{category}', [CategoryController::class, 'show']);
Route::post('/categories', [CategoryController::class, 'store']);
Route::put('/categories/{category}', [CategoryController::class, 'update']);
Route::delete('/categories/{category}', [CategoryController::class, 'destroy']);

Route::get('/chapters', [ChapterController::class, 'index']);
Route::get('/chapters/{chapter}', [ChapterController::class, 'show']);
Route::post('/chapters', [ChapterController::class, 'store']);
Route::put('/chapters/{chapter}', [ChapterController::class, 'update']);
Route::delete('/chapters/{chapter}', [ChapterController::class, 'destroy']);

Route::get('/characters', [CharacterController::class, 'index']);
Route::get('/characters/{character}', [CharacterController::class, 'show']);
Route::post('/characters', [CharacterController::class, 'store']);
Route::put('/characters/{character}', [CharacterController::class, 'update']);
Route::delete('/characters/{character}', [CharacterController::class, 'destroy']);

Route::get('/collections', [CollectionController::class, 'index']);
Route::get('/collections/{collection}', [CollectionController::class, 'show']);
Route::post('/collections', [CollectionController::class, 'store']);
Route::put('/collections/{collection}', [CollectionController::class, 'update']);
Route::delete('/collections/{collection}', [CollectionController::class, 'destroy']);

Route::get('/comments', [CommentController::class, 'index']);
Route::get('/comments/{comment}', [CommentController::class, 'show']);
Route::post('/comments', [CommentController::class, 'store']);
Route::put('/comments/{comment}', [CommentController::class, 'update']);
Route::delete('/comments/{comment}', [CommentController::class, 'destroy']);

Route::get('/languages', [LanguageController::class, 'index']);
Route::get('/languages/{language}', [LanguageController::class, 'show']);

Route::get('/likes', [LikeController::class, 'index']);
Route::get('/likes/{like}', [LikeController::class, 'show']);
Route::post('/likes', [LikeController::class, 'store']);
Route::put('/likes/{like}', [LikeController::class, 'update']);
Route::delete('/likes/{like}', [LikeController::class, 'destroy']);

Route::get('/messages', [MessageController::class, 'index']);
Route::get('/messages/{message}', [MessageController::class, 'show']);
Route::post('/messages', [MessageController::class, 'store']);
Route::put('/messages/{message}', [MessageController::class, 'update']);
Route::delete('/messages/{message}', [MessageController::class, 'destroy']);

Route::get('/ratings', [RatingController::class, 'index']);
Route::get('/ratings/{rating}', [RatingController::class, 'show']);
Route::post('/ratings', [RatingController::class, 'store']);
Route::put('/ratings/{rating}', [RatingController::class, 'update']);
Route::delete('/ratings/{rating}', [RatingController::class, 'destroy']);

Route::get('/tags', [TagController::class, 'index']);
Route::get('/tags/{tag}', [TagController::class, 'show']);
Route::post('/tags', [TagController::class, 'store']);
Route::put('/tags/{tag}', [TagController::class, 'update']);
Route::delete('/tags/{tag}', [TagController::class, 'destroy']);

Route::get('/users', [UserController::class, 'index']);
Route::get('/users/{user}', [UserController::class, 'show']);
Route::post('/users', [UserController::class, 'store']);
Route::put('/users/{user}', [UserController::class, 'update']);
Route::delete('/users/{user}', [UserController::class, 'destroy']);

Route::get('/warnings', [WarningController::class, 'index']);
Route::get('/warnings/{warning}', [WarningController::class, 'show']);
Route::post('/warnings', [WarningController::class, 'store']);
Route::put('/warnings/{warning}', [WarningController::class, 'update']);
Route::delete('/warnings/{warning}', [WarningController::class, 'destroy']);

Route::get('/works', [WorkController::class, 'index']);
Route::get('/works/{work}', [WorkController::class, 'show']);
Route::post('/works', [WorkController::class, 'store']);
Route::put('/works/{work}', [WorkController::class, 'update']);
Route::delete('/works/{work}', [WorkController::class, 'destroy']);
