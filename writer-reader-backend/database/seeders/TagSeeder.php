<?php

namespace Database\Seeders;

use App\Models\Tag;
use App\Models\User;
use App\Models\Work;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class TagSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Tag::factory(25)
            ->recycle(Work::all())
            ->recycle(User::all())
            //->has(Work::factory()->create())
            ->create();
    }
}
