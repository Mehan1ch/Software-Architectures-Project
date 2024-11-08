<?php

namespace Database\Seeders;

use App\Models\Collection;
use App\Models\Comment;
use App\Models\User;
use App\Models\Work;
use Illuminate\Database\Seeder;

class CommentSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Comment::factory(20)
            ->recycle(Work::all())
            ->recycle(User::all())
            //->for(User::factory())
            //->for(Work::factory()->create(),'commentable')
            ->create();

        Comment::factory(20)
            ->recycle(Collection::all())
            ->recycle(User::all())
            //->for(User::factory())
            //->for(Collection::all()->random(),'commentable')
            ->create();
    }
}
