<?php

namespace Database\Seeders;

use App\Models\Category;
use App\Models\Chapter;
use App\Models\Character;
use App\Models\Collection;
use App\Models\Comment;
use App\Models\Like;
use App\Models\Tag;
use App\Models\User;
use App\Models\Warning;
use App\Models\Work;
use Illuminate\Database\Seeder;

class WorkSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Work::withoutEvents(function () {
            Work::factory(50)
                ->has(Chapter::factory()->count(fake()->numberBetween(0, 10)))
                ->has(Warning::factory()->count(fake()->numberBetween(1, 10)))
                ->has(Category::factory()->count(fake()->numberBetween(1, 10)))
                ->has(Tag::factory()->count(fake()->numberBetween(1, 10)))
                ->has(Character::factory()->count(fake()->numberBetween(1,10)))
                //->has(Like::factory()->count(fake()->numberBetween(1,10)))
                //->has(Comment::factory()->count(fake()->numberBetween(1,10)))
                ->create();
        });
    }
}
