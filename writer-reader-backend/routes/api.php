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
use App\Http\Controllers\TokenController;
use App\Http\Controllers\UserController;
use App\Http\Controllers\WarningController;
use App\Http\Controllers\WorkController;
use App\Http\Controllers\CharacterController;
use App\Models\Category;
use App\Models\Chapter;
use App\Models\Character;
use App\Models\Collection;
use App\Models\Comment;
use App\Models\Language;
use App\Models\Like;
use App\Models\Message;
use App\Models\Rating;
use App\Models\Tag;
use App\Models\User;
use App\Models\Warning;
use App\Models\Work;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::group(['middleware' => 'auth:sanctum'], function () {
    Route::get('/user', function (Request $request) {
        return $request->user();
    });

    // Category routes
    Route::post('/categories', [CategoryController::class, 'store'])->can('create', Category::class);
    Route::put('/categories/{category}', [CategoryController::class, 'update'])->can('update', 'category');
    Route::delete('/categories/{category}', [CategoryController::class, 'destroy'])->can('delete', 'category');

    Route::post('/chapters', [ChapterController::class, 'store'])->can('create', Chapter::class);
    Route::put('/chapters/{chapter}', [ChapterController::class, 'update'])->can('update', 'chapter');
    Route::delete('/chapters/{chapter}', [ChapterController::class, 'destroy'])->can('delete', 'chapter');

    Route::post('/characters', [CharacterController::class, 'store'])->can('create', Character::class);
    Route::put('/characters/{character}', [CharacterController::class, 'update'])->can('update', 'character');
    Route::delete('/characters/{character}', [CharacterController::class, 'destroy'])->can('delete', 'character');

    Route::post('/collections', [CollectionController::class, 'store'])->can('create', Collection::class);
    Route::put('/collections/{collection}', [CollectionController::class, 'update'])->can('update', 'collection');
    Route::delete('/collections/{collection}', [CollectionController::class, 'destroy'])->can('delete', 'collection');

    Route::post('/comments', [CommentController::class, 'store'])->can('create', Comment::class);
    Route::put('/comments/{comment}', [CommentController::class, 'update'])->can('update', 'comment');
    Route::delete('/comments/{comment}', [CommentController::class, 'destroy'])->can('delete', 'comment');

    Route::post('/likes', [LikeController::class, 'store'])->can('create', Like::class);
    Route::put('/likes/{like}', [LikeController::class, 'update'])->can('update', 'like');
    Route::delete('/likes/{like}', [LikeController::class, 'destroy'])->can('delete', 'like');

    Route::get('/messages', [MessageController::class, 'index'])->can('viewAny', Message::class);
    Route::get('/messages/{message}', [MessageController::class, 'show'])->can('view', 'message');
    Route::post('/messages', [MessageController::class, 'store'])->can('create', Message::class);
    Route::put('/messages/{message}', [MessageController::class, 'update'])->can('update', 'message');
    Route::delete('/messages/{message}', [MessageController::class, 'destroy'])->can('delete', 'message');

    Route::post('/ratings', [RatingController::class, 'store'])->can('create', Rating::class);
    Route::put('/ratings/{rating}', [RatingController::class, 'update'])->can('update','rating');
    Route::delete('/ratings/{rating}', [RatingController::class, 'destroy'])->can('delete', 'rating');

    Route::post('/tags', [TagController::class, 'store'])->can('create', Tag::class);
    Route::put('/tags/{tag}', [TagController::class, 'update'])->can('update', 'tag');
    Route::delete('/tags/{tag}', [TagController::class, 'destroy'])->can('delete', 'tag');

    Route::post('/users', [UserController::class, 'store'])->can('create', User::class);
    Route::put('/users/{user}', [UserController::class, 'update'])->can('update', 'user');
    Route::delete('/users/{user}', [UserController::class, 'destroy'])->can('delete', 'user');

    Route::post('/warnings', [WarningController::class, 'store'])->can('create', Warning::class);
    Route::put('/warnings/{warning}', [WarningController::class, 'update'])->can('update', 'warning');
    Route::delete('/warnings/{warning}', [WarningController::class, 'destroy'])->can('delete', 'warning');

    Route::post('/works', [WorkController::class, 'store'])->can('create', Work::class);
    Route::put('/works/{work}', [WorkController::class, 'update'])->can('update', 'work');
    Route::delete('/works/{work}', [WorkController::class, 'destroy'])->can('delete', 'work');
});

/*
 * Get routes are accessible to everyone
 */

Route::get('/categories', [CategoryController::class, 'index'])->can('view', Category::class);
Route::get('/categories/{category}', [CategoryController::class, 'show'])->can('view', Category::class);

Route::get('/chapters', [ChapterController::class, 'index'])->can('view', Chapter::class);
Route::get('/chapters/{chapter}', [ChapterController::class, 'show'])->can('view', Chapter::class);

Route::get('/characters', [CharacterController::class, 'index'])->can('view', Character::class);
Route::get('/characters/{character}', [CharacterController::class, 'show'])->can('view', Character::class);

Route::get('/collections', [CollectionController::class, 'index'])->can('view', Collection::class);
Route::get('/collections/{collection}', [CollectionController::class, 'show'])->can('view', Collection::class);

Route::get('/comments', [CommentController::class, 'index'])->can('view', Comment::class);
Route::get('/comments/{comment}', [CommentController::class, 'show'])->can('view', Comment::class);

Route::get('/languages', [LanguageController::class, 'index'])->can('view', Language::class);
Route::get('/languages/{language}', [LanguageController::class, 'show'])->can('view', Language::class);

Route::get('/likes', [LikeController::class, 'index'])->can('view', Like::class);
Route::get('/likes/{like}', [LikeController::class, 'show'])->can('view', Like::class);

Route::get('/ratings', [RatingController::class, 'index'])->can('view', Rating::class);
Route::get('/ratings/{rating}', [RatingController::class, 'show'])->can('view', Rating::class);

Route::get('/tags', [TagController::class, 'index'])->can('view', Tag::class);
Route::get('/tags/{tag}', [TagController::class, 'show'])->can('view', Tag::class);

Route::get('/users', [UserController::class, 'index'])->can('view', User::class);
Route::get('/users/{user}', [UserController::class, 'show'])->can('view', User::class);

Route::get('/warnings', [WarningController::class, 'index'])->can('view', Warning::class);
Route::get('/warnings/{warning}', [WarningController::class, 'show'])->can('view', Warning::class);

Route::get('/works', [WorkController::class, 'index'])->can('view', Work::class);
Route::get('/works/{work}', [WorkController::class, 'show'])->can('view', Work::class);

Route::post('/login', [TokenController::class, 'store'])->middleware('guest');
Route::post('/logout', [TokenController::class, 'destroy'])->middleware('auth:sanctum');

//TODO: language seeding for production (eg: laraworld) see LanguageFatcory.php for more
