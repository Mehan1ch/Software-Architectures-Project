<?php

namespace Database\Seeders;

use App\Models\Collection;
use App\Models\Comment;
use App\Models\Like;
use App\Models\User;
use App\Models\Work;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class LikeSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Like::factory(50)
            ->recycle(User::all())
            ->recycle(Work::all())
            //->for(User::factory())
            //->for(Work::factory()->count(fake()->numberBetween(1,10)), 'likeable')
            ->create();

        Like::factory(50)
            ->recycle(User::all())
            ->recycle(Comment::all())
            //->for(User::factory())
            //->for(Comment::factory()->count(fake()->numberBetween(1,10)), 'likeable')
            ->create();

        Like::factory(50)
            ->recycle(User::all())
            ->recycle(Collection::all())
            //->for(User::factory()->create())
            //->for(Collection::factory()->count(fake()->numberBetween(1,10)), 'likeable')
            ->create();
    }
}
